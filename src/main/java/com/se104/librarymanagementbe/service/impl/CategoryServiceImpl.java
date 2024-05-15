package com.se104.librarymanagementbe.service.impl;

import com.se104.librarymanagementbe.common.RestResponse;
import com.se104.librarymanagementbe.dto.request.CreateCategoryRequest;
import com.se104.librarymanagementbe.dto.request.UpdateCategoryRequest;
import com.se104.librarymanagementbe.dto.response.*;
import com.se104.librarymanagementbe.entity.Category;
import com.se104.librarymanagementbe.repository.CategoryRepository;
import com.se104.librarymanagementbe.service.CategoryService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public abstract class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    @Override
    public RestResponse<GetOneCategoryResponse> getOneCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            GetOneCategoryResponse res = mapper.map(category, GetOneCategoryResponse.class);
            return RestResponse.<GetOneCategoryResponse>builder()
                    .status(HttpStatus.OK.value())
                    .data(res)
                    .build();
        } else {
            return null;
        }
    }

    @Override
    public RestResponse<CreateCategoryResponse> createCategory(CreateCategoryRequest category) {
        Category res = categoryRepository.save(mapper.map(category, Category.class));
        return RestResponse.<CreateCategoryResponse>builder()
                .status(HttpStatus.CREATED.value())
                .data(mapper.map(res, CreateCategoryResponse.class))
                .build();
    }

    @Override
    public RestResponse<UpdateCategoryResponse> updateCategory(UpdateCategoryRequest category, Long id) {
        Optional<Category> oldCategory = categoryRepository.findById(id);
        if (oldCategory.isPresent()) {
            oldCategory.get()
                    .setName(category.getName());
            categoryRepository.save(oldCategory.get());
            return RestResponse.<UpdateCategoryResponse>builder()
                    .status(HttpStatus.OK.value())
                    .data(mapper.map(oldCategory, UpdateCategoryResponse.class))
                    .build();
        } else {
            return null;
        }
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public RestResponse<List<GetListCategoryResponse>> getListCategory() {
        List<Category> categories = categoryRepository.findAll();

        return RestResponse.<List<GetListCategoryResponse>>builder()
                .status(HttpStatus.OK.value())
                .data(categories.stream()
                        .map(user -> mapper.map(categories, GetListCategoryResponse.class))
                        .collect(Collectors.toList()))
                .build();
    }
}
