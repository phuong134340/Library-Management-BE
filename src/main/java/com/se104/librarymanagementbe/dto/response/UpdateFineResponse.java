package com.se104.librarymanagementbe.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UpdateFineResponse {
    long id;
    long total;
    long proceeds;
    long owed;
}
