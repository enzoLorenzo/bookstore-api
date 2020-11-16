package pl.rscorporation.bookstoreapi.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.rscorporation.bookstoreapi.dao.BookRepository;
import pl.rscorporation.bookstoreapi.dao.models.Book;
import java.util.List;

@Repository
interface SqlBookRepository extends BookRepository, JpaRepository<Book, Long> {

}
