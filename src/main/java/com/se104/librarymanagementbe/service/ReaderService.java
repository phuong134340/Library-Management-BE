package com.se104.librarymanagementbe.service;

import com.se104.librarymanagementbe.common.RestResponse;
import com.se104.librarymanagementbe.dto.request.CreateReaderRequest;
import com.se104.librarymanagementbe.dto.request.UpdateReaderRequest;
import com.se104.librarymanagementbe.dto.response.CreateReaderResponse;
import com.se104.librarymanagementbe.dto.response.GetListReaderResponse;
import com.se104.librarymanagementbe.dto.response.GetOneReaderResponse;
import com.se104.librarymanagementbe.dto.response.UpdateReaderResponse;
import com.se104.librarymanagementbe.entity.Reader;
import com.se104.librarymanagementbe.repository.ReaderRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReaderService {
    private final ReaderRepository readerRepository;
    private final ModelMapper mapper;

    public RestResponse<List<GetListReaderResponse>> getListReader() {
        List<Reader> readers = readerRepository.findAll();
        return RestResponse.<List<GetListReaderResponse>>builder()
                .status(HttpStatus.OK.value())
                .data(readers.stream()
                        .map(reader -> mapper.map(reader, GetListReaderResponse.class))
                        .collect(Collectors.toList()))
                .build();
    }

    public RestResponse<GetOneReaderResponse> getOneReader(Long id) {
        Optional<Reader> reader = readerRepository.findById(id);
        if (reader.isPresent()) {
            GetOneReaderResponse res = mapper.map(reader, GetOneReaderResponse.class);
            return RestResponse.<GetOneReaderResponse>builder()
                    .status(HttpStatus.OK.value())
                    .data(res)
                    .build();
        } else {
            return null;
        }
    }

    public RestResponse<CreateReaderResponse> createReader(CreateReaderRequest reader) {
        Reader res = readerRepository.save(mapper.map(reader, Reader.class));
        return RestResponse.<CreateReaderResponse>builder()
                .status(HttpStatus.CREATED.value())
                .data(mapper.map(res, CreateReaderResponse.class))
                .build();
    }

    public RestResponse<UpdateReaderResponse> updateReader(UpdateReaderRequest reader, Long id) {
        Optional<Reader> oldReader = readerRepository.findById(id);
        if (oldReader.isPresent()) {
            oldReader.get()
                    .setFullName(reader.getFullName());
            readerRepository.save(oldReader.get());
            return RestResponse.<UpdateReaderResponse>builder()
                    .status(HttpStatus.OK.value())
                    .data(mapper.map(oldReader, UpdateReaderResponse.class))
                    .build();
        } else {
            return null;
        }
    }

    public void deleteReader(Long id) {
        readerRepository.deleteById(id);
    }
}
