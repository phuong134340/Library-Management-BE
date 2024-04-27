package com.se104.librarymanagementbe.controller;

import com.se104.librarymanagementbe.common.RestResponse;
import com.se104.librarymanagementbe.dto.request.CreateLoanRequest;
import com.se104.librarymanagementbe.dto.request.UpdateLoanRequest;
import com.se104.librarymanagementbe.dto.response.*;
import com.se104.librarymanagementbe.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/loans")
public class LoanController {
    @Autowired
    private LoanService loanService;
    @PostMapping
    public ResponseEntity<RestResponse<CreateLoanResponse>> createLoan(@RequestBody CreateLoanRequest book, CreateLoanRequest loan) {
        return ResponseEntity.ok().body(loanService.createLoan(loan));
    }
    @GetMapping
    public ResponseEntity<RestResponse<List<GetListLoanResponse>>> getListLoan() {
        return ResponseEntity.ok().body(loanService.getListLoans());
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<RestResponse<GetOneLoanResponse>> getOneLoan(@PathVariable Long id) {
        return ResponseEntity.ok().body(loanService.getOneLoan(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<RestResponse<UpdateLoanResponse>> updateBook(@RequestBody UpdateLoanRequest loan, @PathVariable Long id) {
        return ResponseEntity.ok().body(loanService.updateLoan(loan, id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable Long id) {
        loanService.deleteLoan(id);
        return ResponseEntity.noContent().build();
    }
}
