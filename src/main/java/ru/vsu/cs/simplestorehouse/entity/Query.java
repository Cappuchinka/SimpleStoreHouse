package ru.vsu.cs.simplestorehouse.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "Query")
@Data
public class Query {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @NotNull
    private Product product;

    @NotNull
    @Column(name = "count")
    private Integer count;

    @NotNull
    @Column(name = "provider")
    private String provider;
}
