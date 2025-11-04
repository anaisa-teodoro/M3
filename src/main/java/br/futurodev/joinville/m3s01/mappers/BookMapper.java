package br.futurodev.joinville.m3s01.mappers;

import br.futurodev.joinville.m3s01.dtos.BookRequestDto;
import br.futurodev.joinville.m3s01.dtos.BookResponseDto;
import br.futurodev.joinville.m3s01.entities.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "category", ignore = true)
    Book requestCreateToEntity(BookRequestDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "category", ignore = true)
    void requestUpdateToEntity(@MappingTarget Book entity, BookRequestDto dto);

    BookResponseDto entityToResponse(Book entity);

}
