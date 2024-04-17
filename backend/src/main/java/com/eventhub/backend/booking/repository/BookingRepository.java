package com.eventhub.backend.booking.repository;

import com.eventhub.backend.booking.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<BookingEntity, Integer> {
    Optional<BookingEntity> findById(Integer bookingId);

    ArrayList<BookingEntity> findByUserId(Integer userId);

    ArrayList<BookingEntity> findByEventId(Integer eventId);
}
