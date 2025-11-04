package br.futurodev.joinville.m3s01.services;

import br.futurodev.joinville.m3s01.dtos.LoanRequestDto;
import br.futurodev.joinville.m3s01.dtos.LoanResponseDto;
import br.futurodev.joinville.m3s01.entities.Loan;

import java.util.List;

public interface LoanService {

    LoanResponseDto create(LoanRequestDto dto);
    LoanResponseDto findById(Long id);
    List<LoanResponseDto> findAll();

    void returnLoan(Long id);
    void returnBook(Long loanId, Long bookId);

}
