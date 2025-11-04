package br.futurodev.joinville.m3s01.services;

import br.futurodev.joinville.m3s01.dtos.CustomerRequestDto;
import br.futurodev.joinville.m3s01.dtos.UserRequestDto;
import br.futurodev.joinville.m3s01.dtos.UserResponseDto;
import br.futurodev.joinville.m3s01.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserResponseDto create(UserRequestDto dto);
    UserResponseDto findById(Long id);
    List<UserResponseDto> findAll();
    UserResponseDto update(Long id, UserRequestDto dto);
    void delete(Long id);

    User findEntityById(Long id);
    User create(CustomerRequestDto dto);

}
