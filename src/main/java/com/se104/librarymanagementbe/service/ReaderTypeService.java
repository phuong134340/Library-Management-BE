package com.se104.librarymanagementbe.service;

import com.se104.librarymanagementbe.common.RestResponse;
import com.se104.librarymanagementbe.dto.request.CreateReaderTypeRequest;
import com.se104.librarymanagementbe.dto.request.CreateUserRequest;
import com.se104.librarymanagementbe.dto.request.UpdateReaderTypeRequest;
import com.se104.librarymanagementbe.dto.request.UpdateUserRequest;
import com.se104.librarymanagementbe.dto.response.*;

import java.util.List;

public interface ReaderTypeService {
    RestResponse<List<GetListReaderTypeResponse>> getListReaderTypes();


    RestResponse<CreateReaderTypeResponse> createReaderType(CreateReaderTypeRequest readerType);

    RestResponse<UpdateReaderTypeResponse> updateReaderType(UpdateReaderTypeRequest readerType, Long id);

    RestResponse<GetOneReaderTypeResponse> getOneReaderType(Long id);

    void deleteReaderType(Long id);

}
