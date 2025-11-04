package br.futurodev.joinville.m3s01.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponseDto {
    private Long id;
    private String name;
    private String document;
    private String email;
}
