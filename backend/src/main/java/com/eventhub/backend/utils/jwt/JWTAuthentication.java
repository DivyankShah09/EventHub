package com.eventhub.backend.utils.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Base64;

@Component
public class JWTAuthentication {

    private final String jwtSecretKey = "xBrexmbxkkI3L0Dd8wY9EHjpYq6nqqYVOpUr1vtbVFHD9avKZtyfujRCndbcASasbJUZeceG62nacNtDrm3Q2oTDtnIjAcMXVZ";
    private Key hmacKey;

    @PostConstruct
    private void buildHMACKey() {
        System.out.println("jwt : " + jwtSecretKey);
        hmacKey = new SecretKeySpec(Base64.getDecoder().decode(jwtSecretKey),
                SignatureAlgorithm.HS256.getJcaName());
    }

    public String createToken(Integer id, String name) {
        return Jwts.builder()
                .setId(id.toString())
                .setSubject(name)
                .signWith(hmacKey)
                .compact();
    }

    public Integer validateJWTTokenAndGetUserId(String jwtToken) {
        try {
            jwtToken = jwtToken.replace(JWTConstants.PREFIX, "");
            return Integer.parseInt(Jwts.parserBuilder().setSigningKey(hmacKey).build().parseClaimsJws(jwtToken).getBody().getId());
        } catch (Exception e) {
            return null;
        }
    }

    public String extractJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // Extract token part after "Bearer "
        }

        return null;
    }
}
