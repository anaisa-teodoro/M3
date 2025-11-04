package br.futurodev.joinville.m3s01.controllers;

import br.futurodev.joinville.m3s01.dtos.UserRequestDto;
import br.futurodev.joinville.m3s01.dtos.UserResponseDto;
import br.futurodev.joinville.m3s01.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto post(@Valid @RequestBody UserRequestDto dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<UserResponseDto> get() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public UserResponseDto getId(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("{id}")
    public UserResponseDto put(@PathVariable Long id, @Valid @RequestBody UserRequestDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
