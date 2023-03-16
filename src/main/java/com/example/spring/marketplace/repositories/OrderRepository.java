package com.example.spring.marketplace.repositories;

import com.example.spring.marketplace.entities.Order;
import com.example.spring.marketplace.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByUser(User user);
}
