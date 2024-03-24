package com.se104.librarymanagementbe.controller;

import com.se104.librarymanagementbe.common.RestResponse;
import com.se104.librarymanagementbe.dto.request.CreateUserRequest;
import com.se104.librarymanagementbe.dto.request.UpdateUserRequest;
import com.se104.librarymanagementbe.dto.response.CreateUserResponse;
import com.se104.librarymanagementbe.dto.response.GetListUserResponse;
import com.se104.librarymanagementbe.dto.response.GetOneUserResponse;
import com.se104.librarymanagementbe.dto.response.UpdateUserResponse;
import com.se104.librarymanagementbe.entity.User;
import com.se104.librarymanagementbe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<RestResponse<CreateUserResponse>> createUser(@RequestBody CreateUserRequest user) {
        return ResponseEntity.ok().body(userService.createUser(user));
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<GetListUserResponse>>> getListUser(){
        return ResponseEntity.ok().body(userService.getListUsers());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RestResponse<GetOneUserResponse>> getOneUser(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.getOneUser(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<RestResponse<UpdateUserResponse>> updateUser (@RequestBody UpdateUserRequest user,@PathVariable Long id){
        return ResponseEntity.ok().body(userService.updateUser(user,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser (@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
