package com.kafka.checkout.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.kafka.checkout.api.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
