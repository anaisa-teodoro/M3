package br.futurodev.joinville.m3s01.services;

import br.futurodev.joinville.m3s01.dtos.LoginRequestDto;
import br.futurodev.joinville.m3s01.dtos.LoginResponseDto;

public interface LoginService {

    LoginResponseDto authenticate(LoginRequestDto dto);

}
