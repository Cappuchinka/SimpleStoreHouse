package ru.vsu.cs.simplestorehouse.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.vsu.cs.simplestorehouse.entity.Product;

@Data
public class QueryDto {
    @NotNull
    private ProductDto product;

    @NotNull
    private Integer count;

    @NotNull
    private String provider;
}
