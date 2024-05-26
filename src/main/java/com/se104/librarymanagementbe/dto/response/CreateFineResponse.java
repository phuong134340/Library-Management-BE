package com.se104.librarymanagementbe.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CreateFineResponse {
    long id;
    long total;
    long proceeds;
    long owed;
}
