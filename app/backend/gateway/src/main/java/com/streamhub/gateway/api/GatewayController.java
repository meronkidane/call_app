package com.streamhub.gateway.api;

import com.streamhub.common.api.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GatewayController {

    @GetMapping(path = "/internal/gateway-info", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<Map<String, Object>> info() {
        return ApiResponse.success(Map.of(
                "service", "gateway",
                "status", "OK",
                "message", "Edge gateway operational"
        ));
    }
}
