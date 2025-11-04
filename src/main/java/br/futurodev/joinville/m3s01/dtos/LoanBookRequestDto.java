package br.futurodev.joinville.m3s01.dtos;

import jakarta.validation.constraints.NotNull;

public record LoanBookRequestDto(
    @NotNull Long bookId,
    Integer quantity
) {
}
