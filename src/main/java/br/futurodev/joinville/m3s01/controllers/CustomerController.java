package br.futurodev.joinville.m3s01.controllers;

import br.futurodev.joinville.m3s01.dtos.CustomerRequestDto;
import br.futurodev.joinville.m3s01.dtos.CustomerResponseDto;
import br.futurodev.joinville.m3s01.services.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("customers")
public class CustomerController {

    private final CustomerService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponseDto post(@Valid @RequestBody CustomerRequestDto dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<CustomerResponseDto> get() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public CustomerResponseDto getId(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("{id}")
    public CustomerResponseDto put(@PathVariable Long id, @Valid @RequestBody CustomerRequestDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
