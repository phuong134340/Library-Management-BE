package com.se104.librarymanagementbe.controller;

import com.se104.librarymanagementbe.common.RestResponse;
import com.se104.librarymanagementbe.dto.request.CreateAuthorRequest;
import com.se104.librarymanagementbe.dto.request.UpdateAuthorRequest;
import com.se104.librarymanagementbe.dto.response.*;
import com.se104.librarymanagementbe.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;
    @PostMapping
    public ResponseEntity<RestResponse<CreateAuthorResponse>> createAuthor(@RequestBody CreateAuthorRequest author) {
        return ResponseEntity.ok().body(authorService.createAuthor(author));
    }
    @GetMapping
    public ResponseEntity<RestResponse<List<GetListAuthorResponse>>> getListAuthor(){
        return ResponseEntity.ok().body(authorService.getListAuthors());
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<RestResponse<GetOneAuthorResponse>> getOneAuthor(@PathVariable Long id) {
        return ResponseEntity.ok().body(authorService.getOneAuthor(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<RestResponse<UpdateAuthorResponse>> updateAuthor (@RequestBody UpdateAuthorRequest author, @PathVariable Long id){
        return ResponseEntity.ok().body(authorService.updateAuthor(author,id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor (@PathVariable Long id){
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }
}
