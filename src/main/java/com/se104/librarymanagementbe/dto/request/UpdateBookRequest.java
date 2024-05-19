package com.se104.librarymanagementbe.dto.request;

import com.se104.librarymanagementbe.entity.Author;
import com.se104.librarymanagementbe.entity.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@Data
public class UpdateBookRequest {
    String name;
    long categoryId;
    long authorId;
    Instant publishDate;
    String publisher;
    long price;
}
