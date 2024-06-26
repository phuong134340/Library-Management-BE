package com.se104.librarymanagementbe.service;

import com.se104.librarymanagementbe.common.RestResponse;
import com.se104.librarymanagementbe.dto.request.CreateAuthorRequest;
import com.se104.librarymanagementbe.dto.request.UpdateAuthorRequest;
import com.se104.librarymanagementbe.dto.response.CreateAuthorResponse;
import com.se104.librarymanagementbe.dto.response.GetListAuthorResponse;
import com.se104.librarymanagementbe.dto.response.GetOneAuthorResponse;
import com.se104.librarymanagementbe.dto.response.UpdateAuthorResponse;
import com.se104.librarymanagementbe.entity.Author;
import com.se104.librarymanagementbe.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final ModelMapper mapper;


    public RestResponse<GetOneAuthorResponse> getOneAuthor(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            GetOneAuthorResponse res = mapper.map(author, GetOneAuthorResponse.class);
            return RestResponse.<GetOneAuthorResponse>builder()
                    .status(HttpStatus.OK.value())
                    .data(res)
                    .build();
        } else {
            return null;
        }
    }


    public RestResponse<CreateAuthorResponse> createAuthor(CreateAuthorRequest author) {
        Author res = authorRepository.save(mapper.map(author, Author.class));
        return RestResponse.<CreateAuthorResponse>builder()
                .status(HttpStatus.CREATED.value())
                .data(mapper.map(res, CreateAuthorResponse.class))
                .build();
    }


    public RestResponse<UpdateAuthorResponse> updateAuthor(UpdateAuthorRequest author, Long id) {
        Optional<Author> oldAuthor = authorRepository.findById(id);
        if (oldAuthor.isPresent()) {
            if(author.getName() != null){
                oldAuthor.get().setName(author.getName());
            }
            if(author.getAge() != 0){
                oldAuthor.get().setAge(author.getAge());
            }
            if(author.getAddress() != null){
                oldAuthor.get().setAddress(author.getAddress());
            }
            if(author.getNumber() != 0){
                oldAuthor.get().setNumber(author.getNumber());
            }

            authorRepository.save(oldAuthor.get());
            return RestResponse.<UpdateAuthorResponse>builder()
                    .status(HttpStatus.OK.value())
                    .data(mapper.map(oldAuthor, UpdateAuthorResponse.class))
                    .build();
        } else {
            return null;
        }
    }


    public RestResponse<List<GetListAuthorResponse>> getListAuthors() {
        List<Author> authors = authorRepository.findAll();

        return RestResponse.<List<GetListAuthorResponse>>builder()
                .status(HttpStatus.OK.value())
                .data(authors.stream()
                        .map(author -> mapper.map(author, GetListAuthorResponse.class))
                        .collect(Collectors.toList()))
                .build();
    }


    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
