package ru.vsu.cs.simplestorehouse.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class QueryDto {
    @NotNull
    private ProductDto product;

    @NotNull
    private Integer count;

    @NotNull
    private String provider;
}
