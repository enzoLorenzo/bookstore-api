package pl.rscorporation.bookstoreapi.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.rscorporation.bookstoreapi.dao.models.UserMyself;
import pl.rscorporation.bookstoreapi.enums.Roles;

import java.util.Collections;
import java.util.List;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

//    private final UserRepository repository;
//
//    @Autowired
//    public UserDetailsServiceImpl(UserRepository repository) {
//        this.repository = repository;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<UserMyself> userOptional = repository.findByLogin(username);
        UserMyself user = new UserMyself("user", new BCryptPasswordEncoder().encode("user"), Roles.USER);
//        UserMyself user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getKey()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
