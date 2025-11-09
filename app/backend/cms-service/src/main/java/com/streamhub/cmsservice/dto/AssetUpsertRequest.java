package com.streamhub.cmsservice.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record AssetUpsertRequest(
        String id,
        @NotBlank String parentId,
        @NotEmpty List<String> codecs,
        List<String> renditions,
        String drm,
        String hlsUrl,
        String dashUrl,
        List<String> captions,
        List<String> posters) {
}
