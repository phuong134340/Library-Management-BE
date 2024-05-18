package com.se104.librarymanagementbe.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@Data
public class GetOneReturnBookResponse {
    long id;
    String name;
    Instant returnDate;
    long fine;
    long number;
}
