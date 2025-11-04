package br.futurodev.joinville.m3s01.mappers;

import br.futurodev.joinville.m3s01.dtos.CategoryRequestDto;
import br.futurodev.joinville.m3s01.dtos.CategoryResponseDto;
import br.futurodev.joinville.m3s01.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "parentCategory", ignore = true)
    Category requestCreateToEntity(CategoryRequestDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "parentCategory", ignore = true)
    void requestUpdateToEntity(@MappingTarget Category entity, CategoryRequestDto dto);

    CategoryResponseDto entityToResponse(Category entity);

}
