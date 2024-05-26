package com.se104.librarymanagementbe.controller;

import com.se104.librarymanagementbe.common.RestResponse;
import com.se104.librarymanagementbe.dto.request.*;
import com.se104.librarymanagementbe.dto.response.*;
import com.se104.librarymanagementbe.service.FineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/fines")
public class FineController {
    @Autowired
    private FineService fineService;
    @PostMapping
    public ResponseEntity<RestResponse<CreateFineResponse>> createFine(@RequestBody CreateFineRequest fine) {
        return ResponseEntity.ok().body(fineService.createFine(fine));
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<GetListFineResponse>>> getListFine(){
        return ResponseEntity.ok().body(fineService.getListFines());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RestResponse<GetOneFineResponse>> getOneFine(@PathVariable Long id) {
        return ResponseEntity.ok().body(fineService.getOneFine(id));
    }
    @GetMapping(value = "/reader/{readerId}")
    public ResponseEntity<RestResponse<GetOneFineResponse>> getOneFineByReaderId(@PathVariable Long readerId){
        return ResponseEntity.ok().body(fineService.getOneFine(readerId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestResponse<UpdateFineResponse>> updateFine (@RequestBody UpdateFineRequest fine, @PathVariable Long id){
        return ResponseEntity.ok().body(fineService.updateFine(fine,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFine (@PathVariable Long id){
        fineService.deleteFine(id);
        return ResponseEntity.noContent().build();
    }
}
