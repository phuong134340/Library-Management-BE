package com.se104.librarymanagementbe.controller;

import com.se104.librarymanagementbe.common.RestResponse;
import com.se104.librarymanagementbe.dto.request.CreateReturnBookRequest;
import com.se104.librarymanagementbe.dto.response.*;
import com.se104.librarymanagementbe.service.ReturnBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/return_books")
public class ReturnBookController {
    @Autowired
    private ReturnBookService returnBookService;
    @PostMapping
    public ResponseEntity<RestResponse<CreateReturnBookResponse>> createReturnBook(@RequestBody CreateReturnBookRequest returnBook) {
        return ResponseEntity.ok().body(returnBookService.createReturnBook(returnBook));
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<GetListReturnBookResponse>>> getListReturnBook(){
        return ResponseEntity.ok().body(returnBookService.getListReturnBooks());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RestResponse<GetOneReturnBookResponse>> getOneReturnBook(@PathVariable Long id) {
        return ResponseEntity.ok().body(returnBookService.getOneReturnBook(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReturnBook (@PathVariable Long id){
        returnBookService.deleteReturnBook(id);
        return ResponseEntity.noContent().build();
    }
}
