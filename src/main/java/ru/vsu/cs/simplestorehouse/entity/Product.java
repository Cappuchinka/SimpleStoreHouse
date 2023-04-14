package ru.vsu.cs.simplestorehouse.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "price")
    private Float price;

    @NotNull
    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @NotNull
    @Column(name = "percent")
    private Float percent;

    @NotNull
    @Column(name = "count_unit")
    private Integer countUnit;
}
