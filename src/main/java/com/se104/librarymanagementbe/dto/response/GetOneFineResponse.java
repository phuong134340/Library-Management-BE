package com.se104.librarymanagementbe.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class GetOneFineResponse {
    long id;
    long readerId;
    long total;
    long proceeds;
    long owed;
}
