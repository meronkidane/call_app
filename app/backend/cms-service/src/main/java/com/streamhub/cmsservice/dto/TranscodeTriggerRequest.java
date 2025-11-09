package com.streamhub.cmsservice.dto;

import jakarta.validation.constraints.NotBlank;

public record TranscodeTriggerRequest(@NotBlank String sourcePath,
                                     @NotBlank String outputProfile) {
}
