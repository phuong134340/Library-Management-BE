package com.se104.librarymanagementbe.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UpdateFineResponse {
    long id;
    String name;
    long total;
    long money;
    long change;
}
