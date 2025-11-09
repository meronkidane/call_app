package com.streamhub.cmsservice.dto;

import java.time.Instant;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ScheduleRequest(@NotBlank String titleId,
                              @NotNull Instant startAt,
                              @NotNull Instant endAt,
                              List<String> regions) {
}
