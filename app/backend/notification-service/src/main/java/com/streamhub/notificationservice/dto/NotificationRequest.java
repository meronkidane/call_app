package com.streamhub.notificationservice.dto;

import java.util.Map;

import jakarta.validation.constraints.NotBlank;

public record NotificationRequest(@NotBlank String channel,
                                  @NotBlank String recipient,
                                  @NotBlank String template,
                                  Map<String, Object> variables) {
}
