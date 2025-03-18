package org.bot.token;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;

@Component
public class JwtUtil {
    private final Key key;
    private final long EXPIRATION_TIME = 86400000; // 1 day in milliseconds

    public JwtUtil(@Value("${jwt.secret}") String secret) {
        this.key = new SecretKeySpec(secret.getBytes(), HS256.getJcaName());
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, HS256)
                .compact();
    }

    public String validateToken(String token) {
        try {
            return Jwts.parser().setSigningKey(key).build()
                    .parseClaimsJws(token).getBody().getSubject();
        } catch (Exception e) {
            return null;
        }
    }
}
