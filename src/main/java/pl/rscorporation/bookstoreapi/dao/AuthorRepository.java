package pl.rscorporation.bookstoreapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.rscorporation.bookstoreapi.dao.models.Author;
import pl.rscorporation.bookstoreapi.dao.models.Book;

import java.util.List;

import java.util.Optional;

public interface AuthorRepository {
    Optional<Author> findById(Long id);

    List<Author> findAll();

    Author save(Author author);

    boolean existsById(Long id);

    void deleteById(Long id);

}
