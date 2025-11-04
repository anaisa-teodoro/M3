package br.futurodev.joinville.m3s01.services;

import br.futurodev.joinville.m3s01.dtos.AuthorRequestDto;
import br.futurodev.joinville.m3s01.dtos.AuthorResponseDto;
import br.futurodev.joinville.m3s01.entities.Author;

import java.util.List;

public interface AuthorService {

    AuthorResponseDto create(AuthorRequestDto dto);
    AuthorResponseDto findById(Long id);
    List<AuthorResponseDto> findAll();
    AuthorResponseDto update(Long id, AuthorRequestDto dto);
    void delete(Long id);

    Author findEntityById(Long id);

}
