package br.futurodev.joinville.m3s01.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryRequestDto(
    @NotBlank @Size(max = 255) String name,
    String description,
    Long parentCategoryId
) {
}
