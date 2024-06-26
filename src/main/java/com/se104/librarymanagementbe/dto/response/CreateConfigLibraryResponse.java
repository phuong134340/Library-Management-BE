package com.se104.librarymanagementbe.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CreateConfigLibraryResponse {
    long id;
    long ageMax;
    long ageMin;
    long cardValidity;
    long yearOfPublication;
    long limitBook;
    long dayMax;
}
