package com.se104.librarymanagementbe.controller;

import com.se104.librarymanagementbe.common.RestResponse;
import com.se104.librarymanagementbe.dto.request.CreateReaderTypeRequest;
import com.se104.librarymanagementbe.dto.request.CreateUserRequest;
import com.se104.librarymanagementbe.dto.request.UpdateReaderTypeRequest;
import com.se104.librarymanagementbe.dto.request.UpdateUserRequest;
import com.se104.librarymanagementbe.dto.response.*;
import com.se104.librarymanagementbe.service.ReaderTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/readertypes")
public class ReaderTypeController {
    @Autowired
    private ReaderTypeService readerTypeService;
    @PostMapping
    public ResponseEntity<RestResponse<CreateReaderTypeResponse>> createReaderType(@RequestBody CreateReaderTypeRequest readerType) {
        return ResponseEntity.ok().body(readerTypeService.createReaderType(readerType));
    }
    @GetMapping
    public ResponseEntity<RestResponse<List<GetListReaderTypeResponse>>> getListReaderType(){
        return ResponseEntity.ok().body(readerTypeService.getListReaderTypes());
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<RestResponse<GetOneReaderTypeResponse>> getOneReaderType(@PathVariable Long id) {
        return ResponseEntity.ok().body(readerTypeService.getOneReaderType(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<RestResponse<UpdateReaderTypeResponse>> updateReaderType (@RequestBody UpdateReaderTypeRequest readerType, @PathVariable Long id){
        return ResponseEntity.ok().body(readerTypeService.updateReaderType(readerType,id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReaderType (@PathVariable Long id){
        readerTypeService.deleteReaderType(id);
        return ResponseEntity.noContent().build();
    }
}
