package br.futurodev.joinville.m3s01.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CustomerRequestDto(
        @NotBlank @Size(max = 150) String name,
        @Size(max = 50) String document,
        @NotBlank @Size(max = 255) @Email String email,
        @NotBlank String password
) {
}
