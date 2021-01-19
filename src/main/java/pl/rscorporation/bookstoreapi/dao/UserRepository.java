package pl.rscorporation.bookstoreapi.dao;

import pl.rscorporation.bookstoreapi.dao.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    public Optional<User> findByUsername(String username);
    public List<User> findAll();
}
