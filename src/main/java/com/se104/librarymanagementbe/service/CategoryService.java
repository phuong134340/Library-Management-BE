package com.se104.librarymanagementbe.service;

import com.se104.librarymanagementbe.common.RestResponse;
import com.se104.librarymanagementbe.dto.request.CreateCategoryRequest;
import com.se104.librarymanagementbe.dto.request.UpdateCategoryRequest;
import com.se104.librarymanagementbe.dto.response.*;

import java.util.List;

public interface CategoryService {
    RestResponse<GetOneCategoryResponse> getOneCategory(Long id);

    RestResponse<CreateCategoryResponse> createCategory(CreateCategoryRequest category);

    RestResponse<UpdateCategoryResponse> updateCategory(UpdateCategoryRequest category, Long id);

    void deleteCategory(Long id);

    RestResponse<List<GetListCategoryResponse>> getListCategory();
}
