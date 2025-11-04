package br.futurodev.joinville.m3s01.dtos;

import br.futurodev.joinville.m3s01.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequestDto(
    @NotBlank @Size(max = 150) String name,
    @NotBlank @Size(max = 255) @Email String email,
    @NotBlank String password,
    @NotNull UserRole role
) {
}
