package com.edfis.ppmtool.security;

import com.edfis.ppmtool.domain.User;
import io.jsonwebtoken.*;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTTokenProvider {

    private static final Logger log = Logger.getLogger(JWTTokenProvider.class.getName());

    public String generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Date currentDate = new Date(System.currentTimeMillis());
        Date expiryDate = new Date(currentDate.getTime() + SecurityConstants.EXPIRATION_TIME);
        String userId = Long.toString(user.getId());
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", (Long.toString(user.getId())));
        claims.put("username", user.getUsername());
        claims.put("fullName", user.getFullName());
        return Jwts.builder()
                .setSubject(userId)
                .setClaims(claims)
                .setIssuedAt(currentDate)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token);
            return true;
        } catch (SignatureException exception) {
            log.info("Invalid JWT Signature");
        } catch (MalformedJwtException exception) {
            log.info("Invalid JWT Token");
        } catch (ExpiredJwtException exception) {
            log.info("Expired JWT Token");
        } catch (UnsupportedJwtException exception) {
            log.info("Unsupported JWT token");
        } catch (IllegalArgumentException exception) {
            log.info("JWT claims string is empty");
        }
        return false;
    }

    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token).getBody();
        String id = (String) claims.get("id");
        return Long.parseLong(id);
    }
}
