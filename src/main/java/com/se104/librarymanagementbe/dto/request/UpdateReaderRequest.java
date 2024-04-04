package com.se104.librarymanagementbe.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@Data
public class UpdateReaderRequest {
    String fullName;
    Instant dateOfBirth;
    String address;
    String email;
    String phoneNumber;
}
