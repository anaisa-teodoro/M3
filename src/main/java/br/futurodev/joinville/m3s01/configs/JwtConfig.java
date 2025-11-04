package br.futurodev.joinville.m3s01.configs;

import br.futurodev.joinville.m3s01.entities.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtConfig {

    // Chave precise ter no mínimo 64 caracteres
    private final String SECRET_KEY = "sB9R0VwqlXBZgUti5YIjAXNC8G1XMoc1IQvYZDbNvub9hP4HNAeDazKiUcrobA6hRP97nZr469gsGju2";

    public boolean validate(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String extractSubject(String token) {
        return parseClaims(token)
                .getBody()
                .getSubject();
    }

    public Jws<Claims> parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token);
    }

    public String generate(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // -> 10 horas
                .claim("id", user.getId())
                .claim("name", user.getName())
                .claim("email", user.getEmail())
                .claim("role", user.getRole())
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Gera a chave para assinar o token
    private Key getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
