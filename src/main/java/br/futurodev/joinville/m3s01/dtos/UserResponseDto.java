package br.futurodev.joinville.m3s01.dtos;

import br.futurodev.joinville.m3s01.enums.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private UserRole role;
}
