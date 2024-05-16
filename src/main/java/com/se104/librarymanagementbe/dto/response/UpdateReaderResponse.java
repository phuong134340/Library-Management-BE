package com.se104.librarymanagementbe.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@Data
public class UpdateReaderResponse {
    long id;
    String fullName;
    Instant dateOfBirth;
    String address;
    String email;
    String phoneNumber;
}
