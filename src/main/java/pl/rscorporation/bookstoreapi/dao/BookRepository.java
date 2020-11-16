package pl.rscorporation.bookstoreapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.rscorporation.bookstoreapi.dao.models.Author;
import pl.rscorporation.bookstoreapi.dao.models.Book;

import java.util.List;
import java.util.Optional;


public interface BookRepository{
    Optional<Book> findById(Long id);
    List<Book> findByAuthorId(Long id);

    List<Book> findAll();

    Book save(Book author);

    void deleteById(Long id);
}
