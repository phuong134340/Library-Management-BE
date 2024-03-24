package com.se104.librarymanagementbe.common;

import com.se104.librarymanagementbe.dto.response.GetOneUserResponse;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestResponse<T> {
    private int status;
    private T data;
}
