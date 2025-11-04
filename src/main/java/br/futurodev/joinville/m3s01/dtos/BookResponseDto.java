package br.futurodev.joinville.m3s01.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookResponseDto {
    private Long id;
    private String title;
    private String subtitle;
    private CategoryResponseDto category;
    private AuthorResponseDto author;
}
