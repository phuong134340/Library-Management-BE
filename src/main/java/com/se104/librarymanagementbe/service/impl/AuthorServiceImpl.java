package com.se104.librarymanagementbe.service.impl;

import com.se104.librarymanagementbe.common.RestResponse;
import com.se104.librarymanagementbe.dto.CreateAuthorRequest;
import com.se104.librarymanagementbe.dto.request.UpdateAuthorRequest;
import com.se104.librarymanagementbe.dto.response.*;
import com.se104.librarymanagementbe.entity.Author;
import com.se104.librarymanagementbe.repository.AuthorRepository;
import com.se104.librarymanagementbe.service.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final ModelMapper mapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, ModelMapper mapper) {
        this.authorRepository = authorRepository;
        this.mapper = mapper;
    }

    @Override
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

    @Override
    public RestResponse<CreateAuthorResponse> createAuthor(CreateAuthorRequest author) {
        Author res = authorRepository.save(mapper.map(author, Author.class));
        return RestResponse.<CreateAuthorResponse>builder()
                .status(HttpStatus.CREATED.value())
                .data(mapper.map(res, CreateAuthorResponse.class))
                .build();
    }

    @Override
    public RestResponse<UpdateAuthorResponse> updateAuthor(UpdateAuthorRequest author, Long id) {
        Optional<Author> oldAuthor = authorRepository.findById(id);
        if (oldAuthor.isPresent()) {
            authorRepository.save(oldAuthor.get());
            return RestResponse.<UpdateAuthorResponse>builder()
                    .status(HttpStatus.OK.value())
                    .data(mapper.map(oldAuthor, UpdateAuthorResponse.class))
                    .build();
        } else {
            return null;
        }
    }

    @Override
    public RestResponse<List<GetListAuthorResponse>> getListAuthors() {
        List<Author> authors = authorRepository.findAll();

        return RestResponse.<List<GetListAuthorResponse>>builder()
                .status(HttpStatus.OK.value())
                .data(authors.stream()
                        .map(author -> mapper.map(author, GetListAuthorResponse.class))
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
