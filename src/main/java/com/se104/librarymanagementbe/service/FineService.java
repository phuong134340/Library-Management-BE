package com.se104.librarymanagementbe.service;

import com.se104.librarymanagementbe.common.RestResponse;
import com.se104.librarymanagementbe.dto.request.*;
import com.se104.librarymanagementbe.dto.response.*;
import com.se104.librarymanagementbe.entity.Fine;
import com.se104.librarymanagementbe.repository.FineRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FineService {
    private final FineRepository fineRepository;
    private final ModelMapper mapper;

    public RestResponse<List<GetListFineResponse>> getListFines() {
        List<Fine> fines = fineRepository.findAll();

        return RestResponse.<List<GetListFineResponse>>builder()
                .status(HttpStatus.OK.value())
                .data(fines.stream()
                        .map(fine -> mapper.map(fine, GetListFineResponse.class))
                        .collect(Collectors.toList()))
                .build();
    }

    public RestResponse<GetOneFineResponse> getOneFine(Long id) {
        Optional<Fine> fine = fineRepository.findById(id);
        if (fine.isPresent()) {
            GetOneFineResponse res = mapper.map(fine, GetOneFineResponse.class);
            return RestResponse.<GetOneFineResponse>builder()
                    .status(HttpStatus.OK.value())
                    .data(res)
                    .build();
        } else {
            return null;
        }
    }

    public RestResponse<CreateFineResponse> createFine(CreateFineRequest fine) {
        Fine res = fineRepository.save(mapper.map(fine, Fine.class));
        return RestResponse.<CreateFineResponse>builder()
                .status(HttpStatus.CREATED.value())
                .data(mapper.map(res, CreateFineResponse.class))
                .build();
    }

    public RestResponse<UpdateFineResponse> updateFine(UpdateFineRequest fine, Long id) {
        Optional<Fine> oldFine = fineRepository.findById(id);
        if (oldFine.isPresent()) {
            if(fine.getName() != null){
                oldFine.get().setName(fine.getName());
            }
            if(fine.getTotal() != 0){
                oldFine.get().setTotal(fine.getTotal());
            }
            if(fine.getMoney() != 0){
                oldFine.get().setMoney(fine.getMoney());
            }
            if(fine.getChange() != 0){
                oldFine.get().setChange(fine.getChange());
            }
            fineRepository.save(oldFine.get());
            return RestResponse.<UpdateFineResponse>builder()
                    .status(HttpStatus.OK.value())
                    .data(mapper.map(oldFine, UpdateFineResponse.class))
                    .build();
        } else {
            return null;
        }
    }

    public void deleteFine(Long id) {
        fineRepository.deleteById(id);
    }
}
