package br.futurodev.joinville.m3s01.configs;

import br.futurodev.joinville.m3s01.entities.Customer;
import br.futurodev.joinville.m3s01.enums.UserRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtFilter jwtFilter) throws Exception {

        http
                .formLogin(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement((s) ->
                        s.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests((autorize) -> autorize
                        // Public's
                        .requestMatchers("/login").permitAll()

                        // Authors
                        .requestMatchers(HttpMethod.GET, "/authors/**").hasAnyAuthority(
                                UserRole.CUSTOMER.name(),
                                UserRole.ADMIN.name()
                        )
                        .requestMatchers("/authors/**").hasAnyAuthority(
                                UserRole.ADMIN.name()
                        )

                        // Books
                        .requestMatchers(HttpMethod.GET, "/books/**").hasAnyAuthority(
                                UserRole.CUSTOMER.name(),
                                UserRole.ADMIN.name()
                        )
                        .requestMatchers("/books/**").hasAnyAuthority(
                                UserRole.ADMIN.name()
                        )

                        // Categories
                        .requestMatchers(HttpMethod.GET, "/categories/**").hasAnyAuthority(
                                UserRole.CUSTOMER.name(),
                                UserRole.ADMIN.name()
                        )
                        .requestMatchers("/categories/**").hasAnyAuthority(
                                UserRole.ADMIN.name()
                        )

                        // Customers
                        .requestMatchers("/customers/**").hasAnyAuthority(
                                UserRole.ADMIN.name()
                        )

                        // Users
                        .requestMatchers("/users/**").hasAnyAuthority(
                                UserRole.ADMIN.name()
                        )

                        // Loans
                        .requestMatchers("/loans/**").hasAnyAuthority(
                                UserRole.ADMIN.name()
                        )

                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
