package com.eventhub.backend.event.repository;

import com.eventhub.backend.event.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface EventRepository extends JpaRepository<EventEntity, Integer> {

    ArrayList<EventEntity> findAll();

    ArrayList<EventEntity> findByEventOrganizerId(Integer eventOrganizerId);
}
