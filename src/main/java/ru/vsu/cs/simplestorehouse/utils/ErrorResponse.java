package ru.vsu.cs.simplestorehouse.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private LocalDate date;
}
