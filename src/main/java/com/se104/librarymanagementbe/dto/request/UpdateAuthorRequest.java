package com.se104.librarymanagementbe.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UpdateAuthorRequest {
    String name;
    long age;
    String address;
    long number;
}
