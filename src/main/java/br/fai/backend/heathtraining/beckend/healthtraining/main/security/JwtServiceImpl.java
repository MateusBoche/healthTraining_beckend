package br.fai.backend.heathtraining.beckend.healthtraining.main.security;

import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.UserModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JwtServiceImpl implements JwtService {

    private final String secret = "XUFAE3FQG1RLBlgQ93fDSUlj4HfbKi4a1kFl1gDloOg=";


    @Override
    public String getEmailFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    @Override
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    @Override
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    @Override
    public Claims getAllClaimsFromToken(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public boolean isTokenExpired(String token) {
        final Date expirationDate = getExpirationDateFromToken(token);
        return  expirationDate.before(new Date());
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String email = getEmailFromToken(token);
        return email.equals(userDetails.getUsername())
                && !isTokenExpired(token);
    }

    @Override
    public String generateToken(UserDetails userDetails, String fullName, UserModel.UserRole role, String email, int id) {
        final Map<String, Object> claims = new HashMap<>();
        claims.put("email", email);
        claims.put("fullName", fullName);
        claims.put("role", role.name());
        claims.put("id", id);
        return createToken(claims, userDetails.getUsername());
    }

    @Override
    public String createToken(Map<String, Object> claims, String subject) {
        final Date issuedAt = new Date(System.currentTimeMillis());
        final Date expirationDate = new Date(System.currentTimeMillis() +
                1000 * 60 * 60 * 10);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(issuedAt)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
