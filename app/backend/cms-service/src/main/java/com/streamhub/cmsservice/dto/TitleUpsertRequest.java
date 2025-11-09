package com.streamhub.cmsservice.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record TitleUpsertRequest(
        String id,
        @NotBlank String type,
        @NotBlank String name,
        String synopsis,
        int year,
        @NotEmpty List<String> genres,
        List<String> cast,
        List<String> tags,
        double rating,
        String artwork,
        List<String> availabilityRegions,
        boolean premium) {
}
