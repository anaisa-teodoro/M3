package br.futurodev.joinville.m3s01.controllers;

import br.futurodev.joinville.m3s01.dtos.BookRequestDto;
import br.futurodev.joinville.m3s01.dtos.BookResponseDto;
import br.futurodev.joinville.m3s01.services.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("books")
public class BookController {

    private final BookService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponseDto post(@Valid @RequestBody BookRequestDto dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<BookResponseDto> get() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public BookResponseDto getId(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("{id}")
    public BookResponseDto put(@PathVariable Long id, @Valid @RequestBody BookRequestDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
