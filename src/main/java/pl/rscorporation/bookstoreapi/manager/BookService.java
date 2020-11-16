package pl.rscorporation.bookstoreapi.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.rscorporation.bookstoreapi.dao.AuthorRepository;
import pl.rscorporation.bookstoreapi.dao.BookRepository;
import pl.rscorporation.bookstoreapi.dao.dto.BookReadDTO;
import pl.rscorporation.bookstoreapi.dao.dto.BookWriteDTO;
import pl.rscorporation.bookstoreapi.dao.models.Author;
import pl.rscorporation.bookstoreapi.dao.models.Book;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

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

    public BookReadDTO findById(Long id){
        Book toReturn = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Book with this id not exists"));
        return new BookReadDTO(toReturn);
    }

    public List<BookReadDTO> findAll(){
        return bookRepository.findAll().stream()
                .map(book -> new BookReadDTO(book))
                .collect(Collectors.toList());
    }

    public List<BookReadDTO> findAll(Pageable page){
        return bookRepository.findAll(page)
                .stream()
                .map(BookReadDTO::new)
                .collect(Collectors.toList());
    }

    public Book save(BookWriteDTO book){
        Author bookAuthor = authorRepository.findById(book.getAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("Author with given id not exists"));

        return bookRepository.save(book.toBook(bookAuthor));
    }


    public void deleteById(Long id)
    {
        if(!bookRepository.existsById(id))
            throw new IllegalArgumentException("Book with given id not exists");
        bookRepository.deleteById(id);
    }
}
