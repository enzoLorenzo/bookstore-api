package pl.rscorporation.bookstoreapi.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rscorporation.bookstoreapi.security.AuthenticationRequest;
import pl.rscorporation.bookstoreapi.security.JWTUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JWTUtils jwtUtils;


    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) {
        String username = authenticationRequest.getUsername();
        log.info(username + " TRYING TO LOGIN");
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            username.trim(),
                            authenticationRequest.getPassword()
                    ));
        } catch (BadCredentialsException e) {
            log.info(username + " NOT FOUND USERNAME");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        String jwt = jwtUtils.generateToken(userDetails);
        if (jwt != null)
            response.addCookie(jwtUtils.getJWTCookie(jwt));
        log.info(username + " LOGGED IN");
        return ResponseEntity.ok("Zalogowano");
    }

    @PostMapping("/log-out")
    public ResponseEntity<String> logout(HttpServletResponse response) {
        Cookie cookie =  jwtUtils.getJWTCookie(null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return ResponseEntity.ok("Wylogowano");
    }

}
