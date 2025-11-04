package br.futurodev.joinville.m3s01.services;

import br.futurodev.joinville.m3s01.dtos.CustomerRequestDto;
import br.futurodev.joinville.m3s01.dtos.UserRequestDto;
import br.futurodev.joinville.m3s01.dtos.UserResponseDto;
import br.futurodev.joinville.m3s01.entities.Author;
import br.futurodev.joinville.m3s01.entities.User;
import br.futurodev.joinville.m3s01.entities.Category;
import br.futurodev.joinville.m3s01.enums.UserRole;
import br.futurodev.joinville.m3s01.errors.exceptions.UserNotFoundException;
import br.futurodev.joinville.m3s01.mappers.UserMapper;
import br.futurodev.joinville.m3s01.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper mapper;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto create(UserRequestDto dto) {
        User entity = mapper.requestCreateToEntity(dto);

        encryptPassword(entity, dto.password());

        entity = repository.save(entity);
        return mapper.entityToResponse(entity);
    }

    @Override
    public UserResponseDto findById(Long id) {
        return mapper.entityToResponse(findEntityById(id));
    }

    @Override
    public List<UserResponseDto> findAll() {
        return repository.findAll()
                .stream().map(mapper::entityToResponse)
                .toList();
    }

    @Override
    public UserResponseDto update(Long id, UserRequestDto dto) {
        User entity = findEntityById(id);
        mapper.requestUpdateToEntity(entity, dto);

        encryptPassword(entity, dto.password());

        entity = repository.save(entity);
        return mapper.entityToResponse(entity);
    }

    @Override
    public void delete(Long id) {
        User entity = findEntityById(id);
        repository.delete(entity);
    }

    @Override
    public User findEntityById(Long id) {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User create(CustomerRequestDto dto) {
        User entity = mapper.requestCreateToEntity(dto);
        encryptPassword(entity, dto.password());
        return repository.save(entity);
    }

    private void encryptPassword(User entity, String password) {
        entity.setPassword(passwordEncoder.encode(password));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByEmail(username);
        if (user.isPresent()) {
            return user.get();
        }

        if ("admin@email.com".equals(username)) {
            return User.builder()
                    .id(0L)
                    .email(username)
                    .password(passwordEncoder.encode("123"))
                    .name("Administrator")
                    .role(UserRole.ADMIN)
                    .build();
        }

        throw new UsernameNotFoundException(username);
    }
}
