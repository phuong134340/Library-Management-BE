package com.se104.librarymanagementbe.service;

import com.se104.librarymanagementbe.common.RestResponse;
import com.se104.librarymanagementbe.dto.request.CreateBookRequest;
import com.se104.librarymanagementbe.dto.request.UpdateBookRequest;
import com.se104.librarymanagementbe.dto.response.*;

import java.util.List;

public interface BookService {
    RestResponse<GetOneBookResponse> getOneBook(Long id);

    RestResponse<CreateBookResponse> createBook(CreateBookRequest book);

    RestResponse<UpdateBookResponse> updateBook(UpdateBookRequest book, Long id);

    void deleteBook(Long id);

    RestResponse<List<GetListBookResponse>> getListBooks();
}
