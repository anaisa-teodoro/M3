package br.futurodev.joinville.m3s01.services;

import br.futurodev.joinville.m3s01.dtos.CategoryRequestDto;
import br.futurodev.joinville.m3s01.dtos.CategoryResponseDto;
import br.futurodev.joinville.m3s01.entities.Category;

import java.util.List;

public interface CategoryService {

    CategoryResponseDto create(CategoryRequestDto dto);
    CategoryResponseDto findById(Long id);
    List<CategoryResponseDto> findAll();
    CategoryResponseDto update(Long id, CategoryRequestDto dto);
    void delete(Long id);

    Category findEntityById(Long id);

}
