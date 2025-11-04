package br.futurodev.joinville.m3s01.controllers;

import br.futurodev.joinville.m3s01.dtos.AuthorRequestDto;
import br.futurodev.joinville.m3s01.dtos.AuthorResponseDto;
import br.futurodev.joinville.m3s01.services.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("authors")
public class AuthorController {

    private final AuthorService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorResponseDto post(@Valid @RequestBody AuthorRequestDto dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<AuthorResponseDto> get() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public AuthorResponseDto getId(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("{id}")
    public AuthorResponseDto put(@PathVariable Long id, @Valid @RequestBody AuthorRequestDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
