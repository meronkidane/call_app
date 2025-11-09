package com.streamhub.common.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.streamhub.common.api.ApiResponse;

@RestController
@ConditionalOnProperty(prefix = "common.status-controller", name = "enabled", havingValue = "true", matchIfMissing = true)
public class ServiceStatusController {

    private final String applicationName;

    public ServiceStatusController(@Value("${spring.application.name:unknown}") String applicationName) {
        this.applicationName = applicationName;
    }

    @GetMapping(path = "/_status", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<Map<String, Object>> status() {
        return ApiResponse.success(Map.of(
                "service", applicationName,
                "status", "OK"
        ));
    }
}
