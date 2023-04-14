package ru.vsu.cs.simplestorehouse.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProductDto {
    @NotNull
    private String name;

    @NotNull
    private Float price;

    @NotNull
    private LocalDate expirationDate;

    @NotNull
    private Float percent;

    @NotNull
    private Integer countUnit;
}
