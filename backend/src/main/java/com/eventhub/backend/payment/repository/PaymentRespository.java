package com.eventhub.backend.payment.repository;

import com.eventhub.backend.payment.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRespository extends JpaRepository<PaymentEntity, Integer> {

}
