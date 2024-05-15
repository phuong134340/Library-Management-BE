package com.se104.librarymanagementbe.service.impl;

import com.se104.librarymanagementbe.common.RestResponse;
import com.se104.librarymanagementbe.dto.request.CreateConfigLibraryRequest;
import com.se104.librarymanagementbe.dto.response.CreateConfigLibraryResponse;
import com.se104.librarymanagementbe.dto.response.GetOneConfigLibraryResponse;
import com.se104.librarymanagementbe.entity.ConfigLibrary;
import com.se104.librarymanagementbe.repository.ConfigLibraryRepository;
import com.se104.librarymanagementbe.service.ConfigLibraryService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfigLibraryServiceImpl implements ConfigLibraryService {
    private final ConfigLibraryRepository configLibraryRepository;
    private ModelMapper mapper;

    @Override
    public RestResponse<GetOneConfigLibraryResponse> getOneConfigLibrary(Long id) {
        Optional<ConfigLibrary> configLibrary = configLibraryRepository.findById(id);
        if (configLibrary.isPresent()) {
            GetOneConfigLibraryResponse res = mapper.map(configLibrary, GetOneConfigLibraryResponse.class);
            return RestResponse.<GetOneConfigLibraryResponse>builder()
                    .status(HttpStatus.OK.value())
                    .data(res)
                    .build();
        } else {
            return null;
        }
    }

    @Override
    public RestResponse<CreateConfigLibraryResponse> createConfigLibrary(CreateConfigLibraryRequest configLibrary) {
        ConfigLibrary res = configLibraryRepository.save(mapper.map(configLibrary, ConfigLibrary.class));
        return RestResponse.<CreateConfigLibraryResponse>builder()
                .status(HttpStatus.CREATED.value())
                .data(mapper.map(res, CreateConfigLibraryResponse.class))
                .build();
    }
    public GetOneConfigLibraryResponse getLastConfig(){
        List<ConfigLibrary> listConfig = configLibraryRepository.findAllByOrderByCreatedAtDesc();
        GetOneConfigLibraryResponse res = new GetOneConfigLibraryResponse();
        if(!listConfig.isEmpty()) {
            res = mapper.map(listConfig.get(0), GetOneConfigLibraryResponse.class);
        }
        else {
            res.setAgeMin(18);
            res.setAgeMax(55);
            res.setCardValidity(6);
            res.setDayMax(4);
            res.setLimitBook(5);
            res.setYearOfPublication(8);
        }
        return res;
    }
}
