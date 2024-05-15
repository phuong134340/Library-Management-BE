package com.se104.librarymanagementbe.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class GetListAuthorResponse {
    long id;
    String name;
    long age;
    String address;
    long number;
}
