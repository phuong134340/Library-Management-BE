package com.se104.librarymanagementbe.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class GetOneCategoryResponse {
    long id;
    String name;
}
