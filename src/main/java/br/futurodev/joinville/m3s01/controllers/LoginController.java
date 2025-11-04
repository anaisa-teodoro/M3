package br.futurodev.joinville.m3s01.controllers;

import br.futurodev.joinville.m3s01.dtos.LoginRequestDto;
import br.futurodev.joinville.m3s01.dtos.LoginResponseDto;
import br.futurodev.joinville.m3s01.services.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("login")
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public LoginResponseDto post(@RequestBody LoginRequestDto dto) {
        return loginService.authenticate(dto);
    }

}
