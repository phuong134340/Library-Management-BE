package com.se104.librarymanagementbe.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@Data
public class GetListConfigLibraryResponse {
    long id;
    long ageMax;
    long ageMin;
    long cardValidity;
    long yearOfPublication;
    long limitBook;
    long dayMax;
    Instant createdAt;
}
