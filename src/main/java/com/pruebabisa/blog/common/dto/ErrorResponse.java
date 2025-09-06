package com.pruebabisa.blog.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse<T> {

    private int statusCode;

    private T message;

    private String timestamp;

    private String path;
}
