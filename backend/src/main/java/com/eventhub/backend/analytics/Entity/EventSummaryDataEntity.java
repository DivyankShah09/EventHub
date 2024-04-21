package com.eventhub.backend.analytics.Entity;

public class EventSummaryDataEntity {
    private Integer eventId;
    private String eventName;
    private Long eventTotalRevenue;
    private Long eventTotalTickets;

    public EventSummaryDataEntity() {
    }

    public EventSummaryDataEntity(Integer eventId, String eventName, Long eventTotalRevenue, Long eventTotalTickets) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventTotalRevenue = eventTotalRevenue;
        this.eventTotalTickets = eventTotalTickets;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Long getEventTotalRevenue() {
        return eventTotalRevenue;
    }

    public void setEventTotalRevenue(Long eventTotalRevenue) {
        this.eventTotalRevenue = eventTotalRevenue;
    }

    public Long getEventTotalTickets() {
        return eventTotalTickets;
    }

    public void setEventTotalTickets(Long eventTotalTickets) {
        this.eventTotalTickets = eventTotalTickets;
    }

    @Override
    public String toString() {
        return "EventSummaryDataEntity{" +
                "eventId=" + eventId +
                ", eventName='" + eventName + '\'' +
                ", eventTotalRevenue=" + eventTotalRevenue +
                ", eventTotalTickets=" + eventTotalTickets +
                '}';
    }
}
