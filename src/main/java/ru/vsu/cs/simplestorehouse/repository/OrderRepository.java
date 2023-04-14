package ru.vsu.cs.simplestorehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.simplestorehouse.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
