package pl.rscorporation.bookstoreapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.rscorporation.bookstoreapi.dao.dto.BookReadDTO;
import pl.rscorporation.bookstoreapi.dao.dto.BookWriteDTO;
import pl.rscorporation.bookstoreapi.dao.models.Book;
import pl.rscorporation.bookstoreapi.manager.BookService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private BookService bookService;
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping(params = {"!size", "!page", "!sort"})
    public ResponseEntity<List<BookReadDTO>> getBooks() {
        logger.info("Get all books");
        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping
    public ResponseEntity<List<BookReadDTO>> getBooks(Pageable page){
        logger.info("Custom pageable");
        return ResponseEntity.ok(bookService.findAll(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookReadDTO> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody BookWriteDTO book) {
        Book created = bookService.save(book);
        return ResponseEntity.created(URI.create("/" + created.getId())).body(created);
    }


    //?
    @PutMapping
    public Book updateBook(@RequestBody BookWriteDTO book) {
        return bookService.save(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable Long id) {
        bookService.deleteById(id);
        logger.warn("Book with id: " + id + "was deleted");
        return ResponseEntity.noContent().build();
    }

}
