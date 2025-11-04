package br.futurodev.joinville.m3s01.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDto {
    private String type;
    private String token;
}
