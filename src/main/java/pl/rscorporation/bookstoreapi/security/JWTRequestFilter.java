package pl.rscorporation.bookstoreapi.security;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class JWTRequestFilter extends OncePerRequestFilter {

    private final UserDetailsServiceImpl userDetailsService;
    private final JWTUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        log.info("----------REQUEST STARTED----------");
        log.info("URL: " + request.getRequestURL());

        if (!Objects.isNull(cookies)) {
            Optional<Cookie> cookie = Arrays.stream(cookies)
                    .filter(c -> c.getName().equals(SecurityConstants.TOKEN_KEY))
                    .findFirst();
            if (cookie.isPresent()) {
                log.info("Found cookie with TOKEN");
                String jwt = cookie.get().getValue();
                try {
                    String username = jwtUtils.extractUsername(jwt);
                    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                        log.info("User: " + username);
                        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                        validateAndAuth(jwt, userDetails, request, response);
                        refreshCookie(userDetails, response);
                    }
                } catch (ExpiredJwtException e) {
                    log.info("Cookie expired: " + e.getMessage());
                    removeCookie(response);
                }
            } else {
                log.info("Cannot find TOKEN cookie - user not logged in");
            }
        }
        log.info("----------REQUEST ENDED----------");
        filterChain.doFilter(request, response);
    }

    private void validateAndAuth(String jwt, UserDetails userDetails, HttpServletRequest request, HttpServletResponse response) {
        if (jwtUtils.validateToken(jwt, userDetails)) {
            var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities());
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        } else {
            removeCookie(response);
        }
    }

    private void removeCookie(HttpServletResponse response) {
        log.info("Token has expired - user not logged in");
        Cookie removedCookie = jwtUtils.getJWTCookie(null);
        removedCookie.setMaxAge(0);
        response.addCookie(removedCookie);
    }

    private void refreshCookie(UserDetails userDetails, HttpServletResponse response) {
        String jwt = jwtUtils.generateToken(userDetails);
        Cookie cookie = jwtUtils.getJWTCookie(jwt);
        response.addCookie(cookie);
    }
}
