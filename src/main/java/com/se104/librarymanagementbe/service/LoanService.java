package com.se104.librarymanagementbe.service;

import com.se104.librarymanagementbe.common.RestResponse;
import com.se104.librarymanagementbe.dto.request.CreateLoanRequest;
import com.se104.librarymanagementbe.dto.request.UpdateLoanRequest;
import com.se104.librarymanagementbe.dto.response.*;

import java.util.List;

public interface LoanService {
    RestResponse<GetOneLoanResponse> getOneLoan(Long id);

    RestResponse<CreateLoanResponse> createLoan(CreateLoanRequest loan);

    RestResponse<UpdateLoanResponse> updateLoan(UpdateLoanRequest loan, Long id);

    void deleteLoan(Long id);

    RestResponse<List<GetListLoanResponse>> getListLoans();
}
