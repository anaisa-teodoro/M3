package br.futurodev.joinville.m3s01.mappers;

import br.futurodev.joinville.m3s01.dtos.LoanBookRequestDto;
import br.futurodev.joinville.m3s01.dtos.LoanBookResponseDto;
import br.futurodev.joinville.m3s01.dtos.LoanRequestDto;
import br.futurodev.joinville.m3s01.dtos.LoanResponseDto;
import br.futurodev.joinville.m3s01.entities.Loan;
import br.futurodev.joinville.m3s01.entities.LoanBook;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LoanMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "lentDate", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "bookQuantity", ignore = true)
    @Mapping(target = "items", ignore = true)
    Loan requestToEntity(LoanRequestDto dto);

    LoanResponseDto entityToResponse(Loan entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "loan", ignore = true)
    @Mapping(target = "book", ignore = true)
    @Mapping(target = "returnedDate", ignore = true)
    LoanBook requestToEntity(LoanBookRequestDto dto);

    LoanBookResponseDto entityToResponse(LoanBook entity);

}
