package br.futurodev.joinville.m3s01.services;

import br.futurodev.joinville.m3s01.dtos.CustomerRequestDto;
import br.futurodev.joinville.m3s01.dtos.CustomerResponseDto;
import br.futurodev.joinville.m3s01.entities.Customer;

import java.util.List;

public interface CustomerService {

    CustomerResponseDto create(CustomerRequestDto dto);
    CustomerResponseDto findById(Long id);
    List<CustomerResponseDto> findAll();
    CustomerResponseDto update(Long id, CustomerRequestDto dto);
    void delete(Long id);

    Customer findEntityById(Long id);

}
