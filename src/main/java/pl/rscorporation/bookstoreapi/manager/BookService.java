package pl.rscorporation.bookstoreapi.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.rscorporation.bookstoreapi.dao.BookRepository;
import pl.rscorporation.bookstoreapi.dao.models.Book;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB(){
        save(new Book(1L, "1111111111", "tytuł", 11L, BigDecimal.valueOf(24.5), "PLN", LocalDate.of(2002, 1, 2)));
        save(new Book(2L, "2222222222", "tytuł2", 11L, BigDecimal.valueOf(19.9), "PLN", LocalDate.of(2020, 2, 28)));
    }

    public Optional<Book> findById(Long id){
        return bookRepository.findById(id);
    }

    public Iterable<Book> findAll(){
        return bookRepository.findAll();
    }

    public Book save(Book book){
        return bookRepository.save(book);
    }

    public void deleteById(Long id){
        bookRepository.deleteById(id);
    }
}
