package br.futurodev.joinville.m3s01.mappers;

import br.futurodev.joinville.m3s01.dtos.AuthorRequestDto;
import br.futurodev.joinville.m3s01.dtos.AuthorResponseDto;
import br.futurodev.joinville.m3s01.entities.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    @Mapping(target = "id", ignore = true)
    Author requestCreateToEntity(AuthorRequestDto dto);

    @Mapping(target = "id", ignore = true)
    void requestUpdateToEntity(@MappingTarget Author entity, AuthorRequestDto dto);

    AuthorResponseDto entityToResponse(Author entity);

}
