package com.streamhub.catalogservice.dto;

import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record SearchRequest(
        @NotBlank String query,
        List<String> genres,
        @Min(0) int page,
        @Min(1) int size) {
}
