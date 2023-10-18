package com.cybersoft.cozastore.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtHelper {
    @Value("${custom.key}")
    private String secKey;

    private final long durTime = 8 * 60 * 60 * 1000;

    public String genToken(String data) {
        Date now = new Date();
        Date expTime = new Date(now.getTime() + durTime);
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secKey));
        return Jwts.builder()
                .subject(data)
                .expiration(expTime)
                .signWith(key)
                .compact();
    }
    public String validToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secKey));
        return Jwts.parser()
                .verifyWith(key).build()
                .parseSignedClaims(token).getPayload().getSubject();
    }
}
