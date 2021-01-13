package pl.rscorporation.bookstoreapi.dao.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
@NoArgsConstructor
public class UserMyself {
    private String username;
    private String password;
    private List<GrantedAuthority> roles;
}
