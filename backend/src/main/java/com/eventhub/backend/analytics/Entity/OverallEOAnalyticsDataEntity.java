package com.eventhub.backend.analytics.Entity;

import org.springframework.stereotype.Component;

@Component
public class OverallEOAnalyticsDataEntity {
    private Long totalEvents;
    private Long totalTickets;
    private Long totalRevenue;

    public OverallEOAnalyticsDataEntity() {
    }

    public OverallEOAnalyticsDataEntity(Long totalEvents, Long totalTickets, Long totalRevenue) {
        this.totalEvents = totalEvents;
        this.totalTickets = totalTickets;
        this.totalRevenue = totalRevenue;
    }

    public Long getTotalEvents() {
        return totalEvents;
    }

    public void setTotalEvents(Long totalEvents) {
        this.totalEvents = totalEvents;
    }

    public Long getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(Long totalTickets) {
        this.totalTickets = totalTickets;
    }

    public Long getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Long totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    @Override
    public String toString() {
        return "EventOrganizerDashboardDataEntity{" +
                "totalEvents=" + totalEvents +
                ", totalTickets=" + totalTickets +
                ", totalRevenue=" + totalRevenue +
                '}';
    }
}
