package ru.vsu.cs.simplestorehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.simplestorehouse.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
