package com.se104.librarymanagementbe.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CreateFineRequest {
    String name;
    long total;
    long money;
    long change;
}
