package com.se104.librarymanagementbe.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class GetListBookResponse {
    long id;
    String name;
    String author;
    String publisher;
    long price;
}
