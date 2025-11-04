package br.futurodev.joinville.m3s01.services;

import br.futurodev.joinville.m3s01.dtos.BookRequestDto;
import br.futurodev.joinville.m3s01.dtos.BookResponseDto;
import br.futurodev.joinville.m3s01.entities.Book;

import java.util.List;

public interface BookService {

    BookResponseDto create(BookRequestDto dto);
    BookResponseDto findById(Long id);
    List<BookResponseDto> findAll();
    BookResponseDto update(Long id, BookRequestDto dto);
    void delete(Long id);

    Book findEntityById(Long id);

}
