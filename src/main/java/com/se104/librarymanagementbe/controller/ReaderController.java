package com.se104.librarymanagementbe.controller;

import com.se104.librarymanagementbe.common.RestResponse;
import com.se104.librarymanagementbe.dto.request.CreateReaderRequest;
import com.se104.librarymanagementbe.dto.request.UpdateReaderRequest;
import com.se104.librarymanagementbe.dto.response.CreateReaderResponse;
import com.se104.librarymanagementbe.dto.response.GetListReaderResponse;
import com.se104.librarymanagementbe.dto.response.GetOneReaderResponse;
import com.se104.librarymanagementbe.dto.response.UpdateReaderResponse;
import com.se104.librarymanagementbe.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/readers")
public class ReaderController {
    @Autowired
    private ReaderService readerService;

    @PostMapping
    public ResponseEntity<RestResponse<CreateReaderResponse>> createReader (@RequestBody CreateReaderRequest reader){
        return ResponseEntity.ok().body(readerService.createReader(reader));
    }
    @GetMapping
    public ResponseEntity<RestResponse<List<GetListReaderResponse>>> getListReader (){
        return ResponseEntity.ok().body(readerService.getListReader());
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<RestResponse<GetOneReaderResponse>> getOneReader (@PathVariable Long id){
        return ResponseEntity.ok().body(readerService.getOneReader(id));
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<RestResponse<UpdateReaderResponse>> updateReader(@RequestBody UpdateReaderRequest reader, @PathVariable Long id){
        return ResponseEntity.ok().body(readerService.updateReader(reader,id));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteReader (@PathVariable Long id){
        readerService.deleteReader(id);
        return ResponseEntity.noContent().build();
    }
}
