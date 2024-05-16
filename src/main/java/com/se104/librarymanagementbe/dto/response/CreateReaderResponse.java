package com.se104.librarymanagementbe.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@Data
public class CreateReaderResponse {
    long id;
    String name;
    String fullName;
    String readerType;
    Instant dateOfBirth;
    String email;
    String phoneNumber;
    String address;
    String status;
}
