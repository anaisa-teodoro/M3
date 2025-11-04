package br.futurodev.joinville.m3s01.controllers;

import br.futurodev.joinville.m3s01.dtos.LoanRequestDto;
import br.futurodev.joinville.m3s01.dtos.LoanResponseDto;
import br.futurodev.joinville.m3s01.services.LoanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("loans")
public class LoanController {

    private final LoanService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LoanResponseDto post(@Valid @RequestBody LoanRequestDto dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<LoanResponseDto> get() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public LoanResponseDto getId(@PathVariable Long id) {
        return service.findById(id);
    }

    @PatchMapping("{id}/return")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void returnLoan(@PathVariable Long id) {
        service.returnLoan(id);
    }

    @PatchMapping("{id}/items/books/{bookId}/return")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void returnLoan(@PathVariable Long id, @PathVariable Long bookId) {
        service.returnBook(id, bookId);
    }

}
