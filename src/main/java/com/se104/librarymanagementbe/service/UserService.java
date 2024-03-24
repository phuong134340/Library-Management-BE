package com.se104.librarymanagementbe.service;

import com.se104.librarymanagementbe.common.RestResponse;
import com.se104.librarymanagementbe.dto.request.CreateUserRequest;
import com.se104.librarymanagementbe.dto.request.UpdateUserRequest;
import com.se104.librarymanagementbe.dto.response.CreateUserResponse;
import com.se104.librarymanagementbe.dto.response.GetListUserResponse;
import com.se104.librarymanagementbe.dto.response.GetOneUserResponse;
import com.se104.librarymanagementbe.dto.response.UpdateUserResponse;
import com.se104.librarymanagementbe.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    public RestResponse<List<GetListUserResponse>> getListUsers();

    public RestResponse<GetOneUserResponse> getOneUser(Long id);

    public RestResponse<CreateUserResponse> createUser(CreateUserRequest user);

    public RestResponse<UpdateUserResponse> updateUser(UpdateUserRequest user, Long id);

    public void deleteUser(Long id);
}
