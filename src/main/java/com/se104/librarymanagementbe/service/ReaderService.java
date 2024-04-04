package com.se104.librarymanagementbe.service;

import com.se104.librarymanagementbe.common.RestResponse;
import com.se104.librarymanagementbe.dto.request.CreateReaderRequest;
import com.se104.librarymanagementbe.dto.request.UpdateReaderRequest;
import com.se104.librarymanagementbe.dto.response.*;

import java.util.List;

public interface ReaderService {
    RestResponse<List<GetListReaderResponse>> getListReader();
    RestResponse<GetOneReaderResponse> getOneReader(Long id);
    RestResponse<CreateReaderResponse> createReader(CreateReaderRequest reader);
    RestResponse<UpdateReaderResponse> updateReader(UpdateReaderRequest reader, Long id);
    void deleteReader(Long id);
}
