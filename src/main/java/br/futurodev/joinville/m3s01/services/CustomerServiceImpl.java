package br.futurodev.joinville.m3s01.services;

import br.futurodev.joinville.m3s01.dtos.CustomerRequestDto;
import br.futurodev.joinville.m3s01.dtos.CustomerResponseDto;
import br.futurodev.joinville.m3s01.entities.Customer;
import br.futurodev.joinville.m3s01.entities.User;
import br.futurodev.joinville.m3s01.errors.exceptions.CustomerNotFoundException;
import br.futurodev.joinville.m3s01.mappers.CustomerMapper;
import br.futurodev.joinville.m3s01.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper mapper;
    private final UserService userService;
    private final CustomerRepository repository;

    @Override
    public CustomerResponseDto create(CustomerRequestDto dto) {
        Customer entity = mapper.requestCreateToEntity(dto);

        createUser(entity, dto);

        entity = repository.save(entity);
        return mapper.entityToResponse(entity);
    }

    @Override
    public CustomerResponseDto findById(Long id) {
        return mapper.entityToResponse(findEntityById(id));
    }

    @Override
    public List<CustomerResponseDto> findAll() {
        return repository.findAll()
                .stream().map(mapper::entityToResponse)
                .toList();
    }

    @Override
    public CustomerResponseDto update(Long id, CustomerRequestDto dto) {
        Customer entity = findEntityById(id);
        mapper.requestUpdateToEntity(entity, dto);

        entity = repository.save(entity);
        return mapper.entityToResponse(entity);
    }

    @Override
    public void delete(Long id) {
        Customer entity = findEntityById(id);
        repository.delete(entity);
        userService.delete(entity.getUser().getId());
    }

    @Override
    public Customer findEntityById(Long id) {
        return repository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
    }

    private void createUser(Customer entity, CustomerRequestDto dto) {
        User user = userService.create(dto);
        entity.setUser(user);
    }

}
