package com.se104.librarymanagementbe.dto.response;

import com.se104.librarymanagementbe.entity.Reader_Type;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@Data
public class GetListReaderResponse {
    long id;
    String name;
    String fullName;
    Reader_Type readerType;
    Instant dateOfBirth;
    String email;
    String phoneNumber;
    String address;
    String status;
}
