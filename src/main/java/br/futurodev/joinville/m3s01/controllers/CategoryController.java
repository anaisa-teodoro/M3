package br.futurodev.joinville.m3s01.controllers;

import br.futurodev.joinville.m3s01.dtos.CategoryRequestDto;
import br.futurodev.joinville.m3s01.dtos.CategoryResponseDto;
import br.futurodev.joinville.m3s01.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("categories")
public class CategoryController {

    private final CategoryService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponseDto post(@Valid @RequestBody CategoryRequestDto dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<CategoryResponseDto> get() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public CategoryResponseDto getId(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("{id}")
    public CategoryResponseDto put(@PathVariable Long id, @Valid @RequestBody CategoryRequestDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
