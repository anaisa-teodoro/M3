package br.futurodev.joinville.m3s01.mappers;

import br.futurodev.joinville.m3s01.dtos.CustomerRequestDto;
import br.futurodev.joinville.m3s01.dtos.UserRequestDto;
import br.futurodev.joinville.m3s01.dtos.UserResponseDto;
import br.futurodev.joinville.m3s01.entities.User;
import br.futurodev.joinville.m3s01.enums.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    User requestCreateToEntity(UserRequestDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", constant = "CUSTOMER")
    User requestCreateToEntity(CustomerRequestDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    void requestUpdateToEntity(@MappingTarget User entity, UserRequestDto dto);

    UserResponseDto entityToResponse(User entity);

}
