package br.futurodev.joinville.m3s01.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record BookRequestDto(
    @NotBlank @Size(max = 100) String title,
    @Size(max = 100) String subtitle,
    @NotNull Long categoryId,
    @NotNull Long authorId
) {
}
