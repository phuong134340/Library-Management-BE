package com.se104.librarymanagementbe.dto.response;

import com.se104.librarymanagementbe.entity.Book;
import com.se104.librarymanagementbe.entity.Reader;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@Data
public class GetListRentResponse {
    long id;
    long bookId;
    long readerId;
    Instant startDate;
    String status;
}
