package com.se104.librarymanagementbe.controller;

import com.se104.librarymanagementbe.common.RestResponse;
import com.se104.librarymanagementbe.dto.request.CreateBookRequest;
import com.se104.librarymanagementbe.dto.request.UpdateBookRequest;
import com.se104.librarymanagementbe.dto.response.*;
import com.se104.librarymanagementbe.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<RestResponse<CreateBookResponse>> createBook(@RequestBody CreateBookRequest book) {
        return ResponseEntity.ok().body(bookService.createBook(book));
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<GetListBookResponse>>> getListBook() {
        return ResponseEntity.ok().body(bookService.getListBooks());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RestResponse<GetOneBookResponse>> getOneBook(@PathVariable Long id) {
        return ResponseEntity.ok().body(bookService.getOneBook(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestResponse<UpdateBookResponse>> updateBook(@RequestBody UpdateBookRequest book, @PathVariable Long id) {
        return ResponseEntity.ok().body(bookService.updateBook(book, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
