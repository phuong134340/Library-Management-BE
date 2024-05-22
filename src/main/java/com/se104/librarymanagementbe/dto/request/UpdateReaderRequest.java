package com.se104.librarymanagementbe.dto.request;

import com.se104.librarymanagementbe.entity.Reader_Type;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@Data
public class UpdateReaderRequest {
    String fullName;
    long readerTypeId;
    Instant dateOfBirth;
    String email;
    String phoneNumber;
    String address;
}
