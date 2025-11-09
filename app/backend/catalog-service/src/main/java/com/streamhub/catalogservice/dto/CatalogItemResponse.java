package com.streamhub.catalogservice.dto;

import java.util.List;

public record CatalogItemResponse(
        String id,
        String type,
        String name,
        String synopsis,
        int year,
        List<String> genres,
        List<String> cast,
        double rating,
        boolean premium,
        String artwork) {
}
