package ru.vsu.cs.simplestorehouse.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class QueryPostDto {
    @NotNull
    private String productName;

    @NotNull
    private Integer count;

    @NotNull
    private String provider;
}
