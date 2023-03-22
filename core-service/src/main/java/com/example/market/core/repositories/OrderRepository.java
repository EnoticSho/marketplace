package com.example.market.core.repositories;

import com.example.market.core.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
//    Optional<List<Order>> findByUser(User user);
}
