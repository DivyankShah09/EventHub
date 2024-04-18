package com.eventhub.backend.usermanagement.common.repository;

import com.eventhub.backend.usermanagement.common.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
    Optional<CustomerEntity> findByEmail(String email);

    ArrayList<CustomerEntity> findByCity(String city);
}
