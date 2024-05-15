package com.se104.librarymanagementbe.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@Data
public class GetListLoanResponse {
    long id;
    Instant startDate;
    Instant endDate;
    String status;
}
