package com.library.e_library.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class JwtService  {

    private static final String SECRET="TmV3U2VjcmV0S2V5Rm9ySldUU2lnbmluZ1B1cnBvc2VzMTIzNDU2Nzg";
    private final SecretKey secretKey= Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(String userName)
    {
        Map<String, Object> claims=new HashMap<>();
        return Jwts.builder()
                .subject(userName)
                .claims(claims)
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusSeconds(30*60)))
                .signWith(secretKey)
                .compact();
    }

    public Claims extractAllClaims(String token)
    {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build().parseSignedClaims(token).getPayload();
    }

    public String extractUserName(String token)
    {
        return extractAllClaims(token).getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }
}
