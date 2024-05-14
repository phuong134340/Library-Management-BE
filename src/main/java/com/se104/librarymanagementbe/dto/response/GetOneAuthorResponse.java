package com.se104.librarymanagementbe.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class GetOneAuthorResponse {
    String name;
    long age;
    String address;
    long number;
}
