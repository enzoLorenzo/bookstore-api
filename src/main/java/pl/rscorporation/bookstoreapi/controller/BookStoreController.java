package pl.rscorporation.bookstoreapi.controller;

import org.springframework.web.bind.annotation.*;
import pl.rscorporation.bookstoreapi.dao.models.Book;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookStoreController {

    private List<Book> books;

    public BookStoreController() {
        books = new ArrayList<>();
        books.add(new Book(1L, "1111111111", "tytuł", 11L, BigDecimal.valueOf(24.5), "PLN", LocalDate.of(2002, 1, 2)));
        books.add(new Book(2L, "2222222222", "tytuł2", 11L, BigDecimal.valueOf(19.9), "PLN", LocalDate.of(2020, 2, 28)));

    }

    @GetMapping
    public List<Book> getBooks() {
        return books;
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        Optional<Book> book = books.stream().filter(element -> element.getId().equals(id)).findFirst();
        return book.get();
    }

    @PostMapping
    public boolean addBook(@RequestBody Book book) {
        return books.add(book);
    }

    @PutMapping
    public boolean updateBook(@RequestBody Book book) {
        return books.add(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable Long id) {
        books.removeIf(element -> element.getId().equals(id));
    }

}
