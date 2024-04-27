package com.se104.librarymanagementbe.dto.request;

import com.se104.librarymanagementbe.entity.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@Data
public class CreateBookRequest {
    String name;
    Category category;
    String author;
    Instant publishDate;
    String publisher;
    long price;
}
