package pl.rscorporation.bookstoreapi.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.rscorporation.bookstoreapi.dao.models.Book;

import java.util.List;
import java.util.Optional;


public interface BookRepository {
    Optional<Book> findById(Long id);

    List<Book> findByAuthorId(Long id);

    List<Book> findAll();

    Page<Book> findAll(Pageable page);

    boolean existsById(Long id);

    Book save(Book author);

    void deleteById(Long id);
}
