package com.se104.librarymanagementbe.service;

import com.se104.librarymanagementbe.common.RestResponse;
import com.se104.librarymanagementbe.dto.request.CreateConfigLibraryRequest;
import com.se104.librarymanagementbe.dto.response.CreateConfigLibraryResponse;
import com.se104.librarymanagementbe.dto.response.GetOneConfigLibraryResponse;

public interface ConfigLibraryService {
    RestResponse<GetOneConfigLibraryResponse> getOneConfigLibrary(Long id);
    RestResponse<CreateConfigLibraryResponse> createConfigLibrary(CreateConfigLibraryRequest configLibrary);
}
