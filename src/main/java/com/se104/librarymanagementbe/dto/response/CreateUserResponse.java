package com.se104.librarymanagementbe.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@Data
public class CreateUserResponse{
        long id;
        String username;
        String fullname;
}
