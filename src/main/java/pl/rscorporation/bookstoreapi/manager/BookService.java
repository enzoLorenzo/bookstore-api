package pl.rscorporation.bookstoreapi.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.rscorporation.bookstoreapi.dao.AuthorRepository;
import pl.rscorporation.bookstoreapi.dao.BookRepository;
import pl.rscorporation.bookstoreapi.dao.models.Book;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

@Service
public class BookService {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public List<Book> findByAuthorId(Long id){
        return authorRepository.findById(id)
                .map(author -> bookRepository.findByAuthorId(author.getId()))
                .orElseThrow(() -> new IllegalArgumentException("Author with given id not exists"));
    }

    public Optional<Book> findById(Long id){
        return bookRepository.findById(id);
    }

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Book save(Book book){
        return bookRepository.save(book);
    }

    public void deleteById(Long id){
        bookRepository.deleteById(id);
    }
}
