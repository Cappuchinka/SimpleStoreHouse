package ru.vsu.cs.simplestorehouse.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StoreDto {
    @NotNull
    private String name;
}
