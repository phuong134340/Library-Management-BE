package com.se104.librarymanagementbe.service;

import com.se104.librarymanagementbe.common.RestResponse;
import com.se104.librarymanagementbe.dto.CreateAuthorRequest;
import com.se104.librarymanagementbe.dto.request.UpdateAuthorRequest;
import com.se104.librarymanagementbe.dto.response.*;

import java.util.List;

public interface AuthorService {
    RestResponse<GetOneAuthorResponse> getOneAuthor(Long id);

    RestResponse<CreateAuthorResponse> createAuthor(CreateAuthorRequest author);

    RestResponse<UpdateAuthorResponse> updateAuthor(UpdateAuthorRequest author, Long id);

    RestResponse<List<GetListAuthorResponse>> getListAuthors();

    void deleteAuthor(Long id);
}
