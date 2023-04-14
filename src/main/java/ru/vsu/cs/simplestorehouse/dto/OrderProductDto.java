package ru.vsu.cs.simplestorehouse.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class OrderProductDto {
    @NotNull
    private List<OrderDto> orders;

    @NotNull
    private List<ProductDto> products;

    @NotNull
    private Integer count;
}
