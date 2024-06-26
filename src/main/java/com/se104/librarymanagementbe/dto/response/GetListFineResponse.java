package com.se104.librarymanagementbe.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class GetListFineResponse {
    long id;
    long readerId;
    long total;
    long proceeds;
    long owed;
}
