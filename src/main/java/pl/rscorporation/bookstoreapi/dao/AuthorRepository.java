package pl.rscorporation.bookstoreapi.dao;

import pl.rscorporation.bookstoreapi.dao.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {
    Optional<Author> findById(Long id);

    List<Author> findAll();

    Author save(Author author);

    boolean existsById(Long id);

    void deleteById(Long id);

}
