package pl.rscorporation.bookstoreapi.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.rscorporation.bookstoreapi.dao.UserRepository;
import pl.rscorporation.bookstoreapi.dao.models.User;
import pl.rscorporation.bookstoreapi.enums.Roles;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;


    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////        Optional<UserMyself> userOptional = repository.findByLogin(username);
        User user = repository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("Invalid username"));
//        User user = new User("user", new BCryptPasswordEncoder().encode("user"), Roles.USER.getKey());
//        UserMyself user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
