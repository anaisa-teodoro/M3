package br.futurodev.joinville.m3s01.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String pseudonym;
}
