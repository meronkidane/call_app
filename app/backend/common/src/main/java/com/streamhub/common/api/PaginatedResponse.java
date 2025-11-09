package com.streamhub.common.api;

import java.util.List;

public record PaginatedResponse<T>(List<T> items, long total, int page, int size) {
    public static <T> PaginatedResponse<T> of(List<T> items, long total, int page, int size) {
        return new PaginatedResponse<>(items, total, page, size);
    }
}
