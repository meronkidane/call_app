package com.streamhub.analyticsservice.dto;

public record AnalyticsSummaryResponse(long dailyActiveUsers,
                                       long monthlyActiveUsers,
                                       long totalPlaytimeMinutes,
                                       double completionRate,
                                       String topTitle) {
}
