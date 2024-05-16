package com.se104.librarymanagementbe.service;

import com.se104.librarymanagementbe.common.RestResponse;
import com.se104.librarymanagementbe.dto.request.CreateLoanRequest;
import com.se104.librarymanagementbe.dto.request.UpdateLoanRequest;
import com.se104.librarymanagementbe.dto.response.CreateLoanResponse;
import com.se104.librarymanagementbe.dto.response.GetListLoanResponse;
import com.se104.librarymanagementbe.dto.response.GetOneLoanResponse;
import com.se104.librarymanagementbe.dto.response.UpdateLoanResponse;
import com.se104.librarymanagementbe.entity.Loan;
import com.se104.librarymanagementbe.repository.LoanRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LoanService {
    private final LoanRepository loanRepository;
    private final ModelMapper mapper;

    public RestResponse<GetOneLoanResponse> getOneLoan(Long id) {
        Optional<Loan> loan = loanRepository.findById(id);
        if (loan.isPresent()) {
            GetOneLoanResponse res = mapper.map(loan, GetOneLoanResponse.class);
            return RestResponse.<GetOneLoanResponse>builder()
                    .status(HttpStatus.OK.value())
                    .data(res)
                    .build();
        } else {
            return null;
        }
    }


    public RestResponse<CreateLoanResponse> createLoan(CreateLoanRequest loan) {
        Loan res = loanRepository.save(mapper.map(loan, Loan.class));
        return RestResponse.<CreateLoanResponse>builder()
                .status(HttpStatus.CREATED.value())
                .data(mapper.map(res, CreateLoanResponse.class))
                .build();
    }

    public RestResponse<UpdateLoanResponse> updateLoan(UpdateLoanRequest loan, Long id) {
        Optional<Loan> oldLoan = loanRepository.findById(id);
        if (oldLoan.isPresent()) {

            loanRepository.save(oldLoan.get());
            return RestResponse.<UpdateLoanResponse>builder()
                    .status(HttpStatus.OK.value())
                    .data(mapper.map(oldLoan, UpdateLoanResponse.class))
                    .build();
        } else {
            return null;
        }
    }

    public void deleteLoan(Long id) {
        loanRepository.deleteById(id);
    }

    public RestResponse<List<GetListLoanResponse>> getListLoans() {
        List<Loan> loans = loanRepository.findAll();

        return RestResponse.<List<GetListLoanResponse>>builder()
                .status(HttpStatus.OK.value())
                .data(loans.stream()
                        .map(user -> mapper.map(loans, GetListLoanResponse.class))
                        .collect(Collectors.toList()))
                .build();
    }
}
