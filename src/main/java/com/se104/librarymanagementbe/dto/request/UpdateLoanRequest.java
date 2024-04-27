package com.se104.librarymanagementbe.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@Data
public class UpdateLoanRequest {
    Instant startDate;
    Instant endDate;
}
