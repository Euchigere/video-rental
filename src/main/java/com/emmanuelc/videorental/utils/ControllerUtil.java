package com.emmanuelc.videorental.utils;

import com.emmanuelc.videorental.domain.dto.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.time.ZonedDateTime;

public class ControllerUtil {
    private ControllerUtil() {
    }

    public static <T> ResponseEntity<ApiResponse<T>> buildResponseEntity(T data) {
        return buildResponseEntity(data, "success");
    }

    public static <T> ResponseEntity<ApiResponse<T>> buildResponseEntity(T data, String message) {
        final ApiResponse<T> response = new ApiResponse<>();
        response.setMessage(message);
        response.setStatus(true);
        response.setTimestamp(ZonedDateTime.now());
        response.setData(data);

        return ResponseEntity.ok(response);
    }
}
