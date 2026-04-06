package lk.ijse.Zone_Management_Service.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    // Ensure this is at least 32 characters long
    private final String secret = "your_very_long_secret_key_at_least_32_chars_long_ijse_project";

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        // Convert string to SecretKey object (Required for 0.12.x)
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .claims(claims)           // Modern syntax (no 'set')
                .subject(subject)         // Modern syntax (no 'set')
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(key)            // Pass the SecretKey object directly
                .compact();
    }
}