package com.se104.librarymanagementbe.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@NoArgsConstructor
@Data
public class CreateReturnBookRequest {
    List<Long> rentIds;
    String name;
    Instant returnDate;
}
