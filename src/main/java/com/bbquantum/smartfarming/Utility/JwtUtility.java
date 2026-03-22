package com.bbquantum.smartfarming.Utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Component
public class JwtUtility {

    private String jwtSecret =
            "Bsc_final_year_project_for_Ben_Akua_and_Desmond_super_secure_secret_key_2026";

    private int jwtExpirationInMs = 86400000; // 24 hours

    // generate token
    public String generateJwtToken(String username, List<String> roles) {

        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    // extract all claims
    public Claims extractAllClaims(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // generic claim extractor
    public <T> T extractClaim(String token, Function<Claims, T> resolver) {

        Claims claims = extractAllClaims(token);

        return resolver.apply(claims);
    }

    // extract username
    public String getUsername(String token) {

        return extractClaim(token, Claims::getSubject);
    }

    // extract roles
    public List<String> getRoles(String token) {

        return extractAllClaims(token).get("roles", List.class);
    }

    // extract expiration
    public Date getExpiration(String token) {

        return extractClaim(token, Claims::getExpiration);
    }

    // check if token expired
    public boolean isTokenExpired(String token) {

        return getExpiration(token).before(new Date());
    }

    // validate token
    public boolean isTokenValid(String token, String username) {

        final String tokenUsername = getUsername(token);

        return (tokenUsername.equals(username) && !isTokenExpired(token));
    }

}