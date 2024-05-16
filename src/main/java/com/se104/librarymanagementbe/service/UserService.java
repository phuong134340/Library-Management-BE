package com.se104.librarymanagementbe.service;

import com.se104.librarymanagementbe.common.RestResponse;
import com.se104.librarymanagementbe.dto.request.CreateUserRequest;
import com.se104.librarymanagementbe.dto.request.UpdateUserRequest;
import com.se104.librarymanagementbe.dto.response.CreateUserResponse;
import com.se104.librarymanagementbe.dto.response.GetListUserResponse;
import com.se104.librarymanagementbe.dto.response.GetOneUserResponse;
import com.se104.librarymanagementbe.dto.response.UpdateUserResponse;
import com.se104.librarymanagementbe.entity.User;
import com.se104.librarymanagementbe.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    public RestResponse<List<GetListUserResponse>> getListUsers() {
        List<User> users = userRepository.findAll();

        return RestResponse.<List<GetListUserResponse>>builder()
                .status(HttpStatus.OK.value())
                .data(users.stream()
                        .map(user -> mapper.map(user, GetListUserResponse.class))
                        .collect(Collectors.toList()))
                .build();
    }

    public RestResponse<GetOneUserResponse> getOneUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            GetOneUserResponse res = mapper.map(user, GetOneUserResponse.class);
            return RestResponse.<GetOneUserResponse>builder()
                    .status(HttpStatus.OK.value())
                    .data(res)
                    .build();
        } else {
            return null;
        }
    }

    public RestResponse<CreateUserResponse> createUser(CreateUserRequest user) {
        User res = userRepository.save(mapper.map(user, User.class));
        return RestResponse.<CreateUserResponse>builder()
                .status(HttpStatus.CREATED.value())
                .data(mapper.map(res, CreateUserResponse.class))
                .build();
    }

    public RestResponse<UpdateUserResponse> updateUser(UpdateUserRequest user, Long id) {
        Optional<User> oldUser = userRepository.findById(id);
        if (oldUser.isPresent()) {
            oldUser.get()
                    .setFullname(user.getFullname());
            userRepository.save(oldUser.get());
            return RestResponse.<UpdateUserResponse>builder()
                    .status(HttpStatus.OK.value())
                    .data(mapper.map(oldUser, UpdateUserResponse.class))
                    .build();
        } else {
            return null;
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
