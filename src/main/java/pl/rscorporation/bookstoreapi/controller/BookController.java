package pl.rscorporation.bookstoreapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.rscorporation.bookstoreapi.dao.models.Book;
import pl.rscorporation.bookstoreapi.manager.BookService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getBooks() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {

        Book toReturn = bookService.findById(id).get();
        System.out.println(toReturn);
        return ResponseEntity.ok(toReturn);
    }

    @GetMapping("/author/{authorId}")
    public List<Book> getBooksByAuthorId(@PathVariable Long authorId){
        return bookService.findByAuthorId(authorId);
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    @PutMapping
    public Book updateBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable Long id) {
        bookService.deleteById(id);
    }

}
