package com.sb.NotePad.security;

import com.sb.NotePad.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.access.expiration}")
    private long accessExpiration;

    @Value("${jwt.refresh.expiration}")
    private long refreshExpiration;

    private SecretKey getSignInKey(){
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String generateAccessToken(User user){
        Map<String,Object> claims = Map.of(
                "email",user.getEmail(),
                "role",user.getRole().name(),
                "typ","access"
        );
        return generateToken(accessExpiration,claims);
    }

    public String generateRefreshToken(User user){
        Map<String,Object> claims = Map.of(
                "email",user.getEmail(),
                "typ","refresh"
        );
        return generateToken(refreshExpiration,claims);
    }

    public boolean isAccessToken(String token){
        return "access".equals(getAllClaims(token).get("typ",String.class));
    }

    public boolean isRefreshToken(String token){
        return "refresh".equals(getAllClaims(token).get("typ",String.class));
    }

    public boolean isTokenExpired(String token){
        return getAllClaims(token).getExpiration()
                .before(new Date());
    }


    public String extractEmail(String token){
        return getAllClaims(token).getSubject();
    }
    public String extractRole(String token){
        return getAllClaims(token).get("role",String.class);
    }

    public boolean isTokenValid(String token,String email){
        return email.equals(extractEmail(token))
                && !isTokenExpired(token);
    }

    private String generateToken(long expiration, Map<String,Object> claims){
        String email = claims.get("email").toString();
        return Jwts.builder()
                .claims(claims)
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(getSignInKey())
                .compact();
    }
    private Claims getAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
