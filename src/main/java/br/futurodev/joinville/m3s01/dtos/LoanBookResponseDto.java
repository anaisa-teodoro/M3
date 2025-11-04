package br.futurodev.joinville.m3s01.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class LoanBookResponseDto {
    private Long id;
    private BookResponseDto book;
    private Integer quantity;
    private LocalDate returnedDate;
}
