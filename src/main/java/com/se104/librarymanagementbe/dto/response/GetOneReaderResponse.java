package com.se104.librarymanagementbe.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class GetOneReaderResponse {
    long id;
    String name;
    String fullName;
}
