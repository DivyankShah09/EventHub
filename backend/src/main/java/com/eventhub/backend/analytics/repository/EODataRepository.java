package com.eventhub.backend.analytics.repository;

import com.eventhub.backend.usermanagement.common.entity.EventOrganizerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EODataRepository extends JpaRepository<EventOrganizerEntity, Integer> {

    @Query(value = "SELECT COUNT(DISTINCT e.id) AS totalEvents, " +
            "SUM(b.quantity) AS totalTickets, " +
            "SUM(b.total_price) AS totalEarning " +
            "FROM event_organizer as eo " +
            "JOIN event as e on eo.id = e.event_organizer_id " +
            "LEFT JOIN (SELECT * FROM booking) AS b ON e.id = b.event_id " +
            "WHERE eo.id = :organizerId", nativeQuery = true)
    String getOverallEOAnalyticsData(int organizerId);

    @Query(value = "SELECT e.id AS event_id, e.name AS event_name, COALESCE(SUM(b.total_price), 0) AS total_earning, COALESCE(SUM(b.quantity), 0) AS total_tickets FROM event AS e LEFT JOIN booking AS b on e.id = b.event_id WHERE e.event_organizer_id = :organizerId GROUP BY e.id, e.name", nativeQuery = true)
    List<String> getEventSummariesByOrganizerId(Integer organizerId);
}
