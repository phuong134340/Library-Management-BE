package com.se104.librarymanagementbe.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@NoArgsConstructor
@Data
public class GetOneReturnBookResponse {
    List<Long> rentIds;
    long id;
    String name;
    Instant returnDate;
}
