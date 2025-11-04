package br.futurodev.joinville.m3s01.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public record LoanRequestDto(
    @NotNull Long customerId,
    @NotNull LocalDate returnDate,
    @NotNull @Size(min = 1) @Valid List<LoanBookRequestDto> items
) {
}
