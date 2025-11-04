package br.futurodev.joinville.m3s01.mappers;

import br.futurodev.joinville.m3s01.dtos.CustomerRequestDto;
import br.futurodev.joinville.m3s01.dtos.CustomerResponseDto;
import br.futurodev.joinville.m3s01.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Customer requestCreateToEntity(CustomerRequestDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    void requestUpdateToEntity(@MappingTarget Customer entity, CustomerRequestDto dto);

    @Mapping(target = "email", source = "user.email")
    CustomerResponseDto entityToResponse(Customer entity);

}
