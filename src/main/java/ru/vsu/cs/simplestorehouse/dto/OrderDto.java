package ru.vsu.cs.simplestorehouse.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    @NotNull
    private List<StoreDto> stores;
}
