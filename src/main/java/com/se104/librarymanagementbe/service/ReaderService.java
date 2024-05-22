package com.se104.librarymanagementbe.service;

import com.se104.librarymanagementbe.common.RestResponse;
import com.se104.librarymanagementbe.dto.request.CreateReaderRequest;
import com.se104.librarymanagementbe.dto.request.UpdateReaderRequest;
import com.se104.librarymanagementbe.dto.response.*;
import com.se104.librarymanagementbe.entity.Category;
import com.se104.librarymanagementbe.entity.Reader;
import com.se104.librarymanagementbe.entity.Reader_Type;
import com.se104.librarymanagementbe.repository.ReaderRepository;
import com.se104.librarymanagementbe.repository.ReaderTypeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReaderService {
    @Autowired
    private final ReaderTypeRepository readerTypeRepository;
    private final ReaderRepository readerRepository;
    private final ModelMapper mapper;
    private final ConfigLibraryService configLibraryService;
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
        Optional<Reader_Type> readerType = readerTypeRepository.findById(reader.getReaderTypeId());
        if(readerType.isEmpty()){
            return null;
        }
        Reader newReader = mapper.map(reader, Reader.class);
        newReader.setReaderType(readerType.get());
        GetOneConfigLibraryResponse lastConfig = configLibraryService.getLastConfig();
        Instant now = Instant.now();

        LocalDateTime dateTime = LocalDateTime.ofInstant(now, ZoneId.systemDefault());

        LocalDateTime expiredDate = dateTime.plus(Period.ofMonths( (int)(lastConfig.getCardValidity() )));
        Instant expiredDateInstant = expiredDate.atZone(ZoneId.systemDefault()).toInstant();
        newReader.setExpiredDate(expiredDateInstant);
        readerRepository.save(newReader);
        return RestResponse.<CreateReaderResponse>builder()
                .status(HttpStatus.CREATED.value())
                .data(mapper.map(newReader, CreateReaderResponse.class))
                .build();
    }

    public RestResponse<UpdateReaderResponse> updateReader(UpdateReaderRequest reader, Long id) {
        Optional<Reader> oldReader = readerRepository.findById(id);
        if (oldReader.isPresent()) {
           if(reader.getFullName() != null){
               oldReader.get().setFullName(reader.getFullName());
           }
           if(reader.getReaderTypeId() != 0){
               Optional<Reader_Type> readerType = readerTypeRepository.findById(reader.getReaderTypeId());
               if(readerType.isEmpty()){
                   return null;
               }else{
                   oldReader.get().setReaderType(readerType.get());
               }
           }
           if(reader.getDateOfBirth() != null){
               oldReader.get().setDateOfBirth(reader.getDateOfBirth());
           }
           if(reader.getAddress() != null){
               oldReader.get().setAddress(reader.getAddress());
           }
           if(reader.getEmail() != null){
               oldReader.get().setEmail(reader.getEmail());
           }
           if(reader.getPhoneNumber() != null){
               oldReader.get().setPhoneNumber(reader.getPhoneNumber());
           }
           readerRepository.save(oldReader.get());
           return RestResponse.<UpdateReaderResponse>builder()
                   .status(HttpStatus.OK.value())
                   .data(mapper.map(oldReader,UpdateReaderResponse.class))
                   .build();
        } else {
            return null;
        }
    }

    public void deleteReader(Long id) {
        readerRepository.deleteById(id);
    }
}
