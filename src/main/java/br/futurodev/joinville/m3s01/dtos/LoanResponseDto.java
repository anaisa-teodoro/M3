package br.futurodev.joinville.m3s01.dtos;

import br.futurodev.joinville.m3s01.enums.LoanStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class LoanResponseDto {
    private Long id;
    private CustomerResponseDto customer;
    private LocalDate lentDate;
    private LocalDate returnDate;
    private LoanStatus status;
    private Integer bookQuantity;
    private List<LoanBookResponseDto> items;
}
