package com.eventhub.backend.usermanagement.common.repository;

import com.eventhub.backend.usermanagement.common.entity.EventOrganizerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventOrganizerRepository extends JpaRepository<EventOrganizerEntity, Integer> {
    Optional<EventOrganizerEntity> findByEmail(String email);
}
