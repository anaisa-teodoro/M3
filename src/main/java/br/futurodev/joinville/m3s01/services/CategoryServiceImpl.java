package br.futurodev.joinville.m3s01.services;

import br.futurodev.joinville.m3s01.dtos.CategoryRequestDto;
import br.futurodev.joinville.m3s01.dtos.CategoryResponseDto;
import br.futurodev.joinville.m3s01.entities.Category;
import br.futurodev.joinville.m3s01.errors.exceptions.CategoryNotFoundException;
import br.futurodev.joinville.m3s01.mappers.CategoryMapper;
import br.futurodev.joinville.m3s01.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper mapper;
    private final CategoryRepository repository;

    @Override
    public CategoryResponseDto create(CategoryRequestDto dto) {
        Category entity = mapper.requestCreateToEntity(dto);

        enrichCategory(entity, dto);

        entity = repository.save(entity);
        return mapper.entityToResponse(entity);
    }

    @Override
    public CategoryResponseDto findById(Long id) {
        return mapper.entityToResponse(findEntityById(id));
    }

    @Override
    public List<CategoryResponseDto> findAll() {
        return repository.findAll()
                .stream().map(mapper::entityToResponse)
                .toList();
    }

    @Override
    public CategoryResponseDto update(Long id, CategoryRequestDto dto) {
        Category entity = findEntityById(id);
        mapper.requestUpdateToEntity(entity, dto);

        enrichCategory(entity, dto);

        entity = repository.save(entity);
        return mapper.entityToResponse(entity);
    }

    @Override
    public void delete(Long id) {
        Category entity = findEntityById(id);
        repository.delete(entity);
    }

    @Override
    public Category findEntityById(Long id) {
        return repository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
    }

    private void enrichCategory(Category entity, CategoryRequestDto dto) {
        Category parent = null;
        if (dto.parentCategoryId() != null) {
            parent = findEntityById(dto.parentCategoryId());
        }
        entity.setParentCategory(parent);
    }

}
