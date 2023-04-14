package ru.vsu.cs.simplestorehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.simplestorehouse.entity.Store;

import java.math.BigInteger;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {
}
