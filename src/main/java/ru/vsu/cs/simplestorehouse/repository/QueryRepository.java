package ru.vsu.cs.simplestorehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.simplestorehouse.entity.Product;
import ru.vsu.cs.simplestorehouse.entity.Query;

@Repository
public interface QueryRepository extends JpaRepository<Query, Integer> {
    @org.springframework.data.jpa.repository.Query("SELECT p FROM Product p WHERE p.name = :name")
    Product findProductByName(String name);
}
