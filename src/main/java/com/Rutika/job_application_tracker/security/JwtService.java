package com.Rutika.job_application_tracker.security;


import com.Rutika.job_application_tracker.entities.User;
import com.Rutika.job_application_tracker.enums.Role;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService
{
    @Value("${jwt.secret}")
    private String SECRET;

    @Value("${jwt.expiration}")
    private Long EXPIRATION_TIME;

    public String generateToken(CustomUserDetails user)
    {
        Map<String,Object> claim =new HashMap<>();
        claim.put("role", user.getRole().name());
        return Jwts.builder()
                .setClaims(claim)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .signWith(getSignKey())
                .compact();
    }

    private Key getSignKey()
    {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }


    public String extractUserName(String token)
    {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenValid(String token,UserDetails userDetails)
    {
        String username = extractUserName(token);

        return username.equals(userDetails.getUsername())
                && !isTokenExpired(token);

    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();

        return expiration.before(new Date());
    }

}
