package pl.rscorporation.bookstoreapi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static pl.rscorporation.bookstoreapi.security.SecurityConstants.EXPIRATION_TIME;
import static pl.rscorporation.bookstoreapi.security.SecurityConstants.TOKEN_KEY;

@Component
public class JWTUtils {

    private String KEY = "KO5EDKnx2M";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
    }

    public Cookie getJWTCookie(String jwt) {
        Cookie cookie = new Cookie(TOKEN_KEY, jwt);
        cookie.setHttpOnly(true);
        cookie.setMaxAge((int) EXPIRATION_TIME * 60);
        cookie.setPath("/");
        //cookie.setSecure(true);
        return cookie;
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME * 1000 * 60))
                .signWith(SignatureAlgorithm.HS256, "KO5EDKnx2M").compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

}
