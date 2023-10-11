package com.example.demo.util;

import com.example.demo.properties.JWTProperty;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    @Autowired
    private JWTProperty jwtProperty;

    SecretKey key;

    SecretKey getSecretKey() {
        if (key == null) {
            key = Keys.hmacShaKeyFor(jwtProperty.getSecret().getBytes());
        }

        return key;
    }

    public String generateToken(Long id) {
        Long expiration = System.currentTimeMillis() + jwtProperty.getExpiration();

        String token = Jwts.builder()
                .signWith(getSecretKey())
                .setSubject(id.toString())
                .setIssuedAt(new Date()).setExpiration(new Date(expiration))
                .compact();

        return token;
    }

    public Pair<Claims, Exception> parseToken(String token) {
        try {

            Claims claims = Jwts
                    .parserBuilder()
                    .setSigningKey(getSecretKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return Pair.of(claims, null);
        } catch (Exception e) {

            return Pair.of(null, e);
        }
    }
}
