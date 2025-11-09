package com.streamhub.common.api;

import java.time.Instant;

public record ApiResponse<T>(T data, Metadata metadata) {

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(data, Metadata.success());
    }

    public static <T> ApiResponse<T> success(T data, Metadata metadata) {
        return new ApiResponse<>(data, metadata);
    }

    public static ApiResponse<Void> success() {
        return new ApiResponse<>(null, Metadata.success());
    }

    public static ApiResponse<Void> error(String code, String message) {
        return new ApiResponse<>(null, Metadata.error(code, message));
    }

    public record Metadata(String status, String code, String message, Instant timestamp, String traceId) {
        public static Metadata success() {
            return new Metadata("SUCCESS", null, null, Instant.now(), null);
        }

        public static Metadata error(String code, String message) {
            return new Metadata("ERROR", code, message, Instant.now(), null);
        }

        public Metadata withTraceId(String traceId) {
            return new Metadata(status, code, message, timestamp, traceId);
        }
    }
}
