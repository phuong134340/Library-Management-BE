package com.se104.librarymanagementbe.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CreateConfigLibraryRequest
{
    long ageMax;
    long ageMin;
    long cardValidity;
    long yearOfPublication;
    long limitBook;
    long dayMax;
}
