package com.se104.librarymanagementbe.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@Data
public class CreateReaderRequest {
    String name;
    String fullName;
    long readerTypeId;
    Instant dateOfBirth;
    String email;
    String phoneNumber;
    String address;
    String status;
}
