package com.se104.librarymanagementbe.dto.response;

import com.se104.librarymanagementbe.entity.Author;
import com.se104.librarymanagementbe.entity.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class GetOneBookResponse {
    long id;
    String name;
    Category category;
    Author author;
    String publisher;
    long price;
}
