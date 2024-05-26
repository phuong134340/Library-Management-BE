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

import java.util.ArrayList;
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
        List<GetListFineResponse> finesResponse = new ArrayList<GetListFineResponse>();
        for (int i = 0; i < fines.size(); i++) {
            GetListFineResponse fine = mapper.map(fines.get(i), GetListFineResponse.class);
            fine.setReaderId(fines.get(i).getReader().getId());
            finesResponse.add(fine);
        }
        return RestResponse.<List<GetListFineResponse>>builder()
                .status(HttpStatus.OK.value())
                .data(finesResponse)
                .build();
    }

    public RestResponse<GetOneFineResponse> getOneFine(Long id) {
        Optional<Fine> fine = fineRepository.findById(id);
        if (fine.isPresent()) {
            GetOneFineResponse res = mapper.map(fine, GetOneFineResponse.class);
            res.setReaderId(fine.get().getReader().getId());
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
            if(fine.getProceeds() != 0){
                if(oldFine.get().getProceeds() + fine.getProceeds() <= oldFine.get().getTotal()){
                    oldFine.get().setProceeds( oldFine.get().getProceeds() + fine.getProceeds() );
                }
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
