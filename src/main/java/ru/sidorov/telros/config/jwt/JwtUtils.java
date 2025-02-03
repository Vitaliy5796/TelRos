package ru.sidorov.telros.config.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ru.sidorov.telros.models.entities.User;
import ru.sidorov.telros.models.exception.NotFoundUserException;
import ru.sidorov.telros.repositories.UserRepository;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(JwtUtils.class);


    @Value("${jwt.token.secret}")
    private String jwtSecretKeyEncodedString;

    @Value("${jwt.token.expired}")
    private long jwtExpiration;

    private Key jwtSecretKey;

    private final UserRepository userRepository;

    public JwtUtils(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public String generateJwtToken(Authentication authentication) {
        UserDetails details = (UserDetails) authentication.getPrincipal();
        log.info("IN generateJwtToken - UserDetails with username: " + details.getUsername() + " password: " + details.getPassword());
        return Jwts.builder().setSubject(details.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + jwtExpiration * 1000))
                .signWith(jwtSecretKey).compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException | IllegalArgumentException e) {
            throw new RuntimeException("JWT token are invalid", e);
        }
    }

    public String getUserLoginFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token).getBody().getSubject();
    }


    @PostConstruct
    private void getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecretKeyEncodedString);
        jwtSecretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public User getUserFromToken(String token) {
        String login = getUserLoginFromToken(token);
        return userRepository.findByEmail(login).orElseThrow(NotFoundUserException::new);
    }

    public String getTokenFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader("Authorization");
        if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }
}
