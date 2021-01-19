package pl.rscorporation.bookstoreapi.dao.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import pl.rscorporation.bookstoreapi.enums.Roles;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMyself {
    private String username;
    private String password;
    private Roles role;
}
