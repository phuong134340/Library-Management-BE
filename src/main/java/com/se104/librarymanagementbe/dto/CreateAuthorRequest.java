package com.se104.librarymanagementbe.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CreateAuthorRequest {
    String name;
    long age;
    String address;
    long number;
}
