package ru.vsu.cs.simplestorehouse.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "order_product")
@Data
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToMany
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @NotNull
    private List<Order> orders;

    @ManyToMany
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @NotNull
    private List<Product> products;

    @NotNull
    @Column(name = "count")
    private Integer count;
}
