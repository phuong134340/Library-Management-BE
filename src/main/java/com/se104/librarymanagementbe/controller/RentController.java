package com.se104.librarymanagementbe.controller;

import com.se104.librarymanagementbe.common.RestResponse;
import com.se104.librarymanagementbe.dto.request.CreateRentRequest;
import com.se104.librarymanagementbe.dto.request.UpdateRentRequest;
import com.se104.librarymanagementbe.dto.response.*;
import com.se104.librarymanagementbe.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/rents")
public class RentController {
    @Autowired
    private RentService rentService;
    @PostMapping
    public ResponseEntity<RestResponse<CreateRentResponse>> createRent(@RequestBody CreateRentRequest rent) {
        return ResponseEntity.ok().body(rentService.createRent(rent));
    }
    @GetMapping
    public ResponseEntity<RestResponse<List<GetListRentResponse>>> getListRent() {
        return ResponseEntity.ok().body(rentService.getListRents());
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<RestResponse<GetOneRentResponse>> getOneRent(@PathVariable Long id) {
        return ResponseEntity.ok().body(rentService.getOneRent(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<RestResponse<UpdateRentResponse>> updateRent(@RequestBody UpdateRentRequest rent, @PathVariable Long id) {
        return ResponseEntity.ok().body(rentService.updateRent(rent, id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRent(@PathVariable Long id) {
        rentService.deleteRent(id);
        return ResponseEntity.noContent().build();
    }
}
