package com.se104.librarymanagementbe.dto.response;

import com.se104.librarymanagementbe.entity.Author;
import com.se104.librarymanagementbe.entity.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@Data
public class UpdateBookResponse {
    long id;
    Long authorId;
    String name;
    Category category;
    Author author;
    Instant publishDate;
    String publisher;
    long price;
}
