package com.se104.librarymanagementbe.service.impl;

import com.se104.librarymanagementbe.common.RestResponse;
import com.se104.librarymanagementbe.dto.request.CreateReaderTypeRequest;
import com.se104.librarymanagementbe.dto.request.UpdateReaderTypeRequest;
import com.se104.librarymanagementbe.dto.response.*;
import com.se104.librarymanagementbe.entity.Reader_Type;
import com.se104.librarymanagementbe.entity.User;
import com.se104.librarymanagementbe.repository.ReaderTypeRepository;
import com.se104.librarymanagementbe.service.ReaderTypeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReaderTypeServiceImpl implements ReaderTypeService {
    final private ReaderTypeRepository readerTypeRepository;
    final private ModelMapper mapper;
    @Override
    public RestResponse<List<GetListReaderTypeResponse>> getListReaderTypes() {
        List<Reader_Type> readerTypes = readerTypeRepository.findAll();

        return RestResponse.<List<GetListReaderTypeResponse>>builder()
                .status(HttpStatus.OK.value())
                .data(readerTypes.stream()
                        .map(readerType -> mapper.map(readerType, GetListReaderTypeResponse.class))
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public RestResponse<CreateReaderTypeResponse> createReaderType(CreateReaderTypeRequest readerType) {
        Reader_Type res = readerTypeRepository.save(mapper.map(readerType, Reader_Type.class));
        return RestResponse.<CreateReaderTypeResponse>builder()
                .status(HttpStatus.CREATED.value())
                .data(mapper.map(res, CreateReaderTypeResponse.class))
                .build();
    }

    @Override
    public RestResponse<UpdateReaderTypeResponse> updateReaderType(UpdateReaderTypeRequest readerType, Long id) {
        Optional<Reader_Type> oldReaderType = readerTypeRepository.findById(id);
        if (oldReaderType.isPresent()) {
            oldReaderType.get()
                    .setName(readerType.getName());
            readerTypeRepository.save(oldReaderType.get());
            return RestResponse.<UpdateReaderTypeResponse>builder()
                    .status(HttpStatus.OK.value())
                    .data(mapper.map(oldReaderType, UpdateReaderTypeResponse.class))
                    .build();
        } else {
            return null;
        }
    }

    @Override
    public RestResponse<GetOneReaderTypeResponse> getOneReaderType(Long id) {
        Optional<Reader_Type> readerType = readerTypeRepository.findById(id);
        if (readerType.isPresent()) {
            GetOneReaderTypeResponse res = mapper.map(readerType, GetOneReaderTypeResponse.class);
            return RestResponse.<GetOneReaderTypeResponse>builder()
                    .status(HttpStatus.OK.value())
                    .data(res)
                    .build();
        } else {
            return null;
        }
    }

    @Override
    public void deleteReaderType(Long id) {
        readerTypeRepository.deleteById(id);
    }
}
