package com.AjoPay.AjoPay.dto.response;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private String message;
    private String statusCode;
    private T data;
    private final LocalDateTime time = LocalDateTime.now();
}
