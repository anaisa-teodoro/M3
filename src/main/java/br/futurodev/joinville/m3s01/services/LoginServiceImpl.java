package br.futurodev.joinville.m3s01.services;

import br.futurodev.joinville.m3s01.configs.JwtConfig;
import br.futurodev.joinville.m3s01.dtos.LoginRequestDto;
import br.futurodev.joinville.m3s01.dtos.LoginResponseDto;
import br.futurodev.joinville.m3s01.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final JwtConfig jwtConfig;
    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponseDto authenticate(LoginRequestDto dto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.email(), dto.password()
                )
        );

        if (!authentication.isAuthenticated()) {
            throw new BadCredentialsException("Invalid email and/or password");
        }

        User user = (User) authentication.getPrincipal();
        String token = jwtConfig.generate(user);
        return LoginResponseDto.builder()
                .type("Bearer")
                .token(token)
                .build();
    }
}
