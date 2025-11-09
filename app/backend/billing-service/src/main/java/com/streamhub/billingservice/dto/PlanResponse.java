package com.streamhub.billingservice.dto;

import java.math.BigDecimal;

public record PlanResponse(String code, String name, BigDecimal price, String currency, int maxDevices, String maxResolution, boolean drmRequired) {
}
