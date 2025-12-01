// com.example.pt_online.exception.CustomBadRequestException.java
package com.example.pt_online.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Tùy chọn: Dùng @ResponseStatus để tự động thiết lập mã lỗi HTTP
@ResponseStatus(HttpStatus.BAD_REQUEST) // HTTP 400
public class CustomBadRequestException extends RuntimeException {

    // Constructor cơ bản
    public CustomBadRequestException(String message) {
        super(message);
    }

    // Constructor bổ sung (nếu cần truyền lỗi gốc)
    public CustomBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}