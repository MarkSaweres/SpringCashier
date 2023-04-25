package com.example.springcashier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashierOrderRepository extends JpaRepository<Order, Long> {
}
