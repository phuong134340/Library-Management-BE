package com.se104.librarymanagementbe.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class GetListFineResponse {
    long id;
    String name;
    long total;
    long money;
    long change;
}
