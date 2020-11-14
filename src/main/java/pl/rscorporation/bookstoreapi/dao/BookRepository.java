package pl.rscorporation.bookstoreapi.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.rscorporation.bookstoreapi.dao.models.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
}
