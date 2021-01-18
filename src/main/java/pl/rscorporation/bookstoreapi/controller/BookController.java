package pl.rscorporation.bookstoreapi.controller;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.rscorporation.bookstoreapi.RestNames;
import pl.rscorporation.bookstoreapi.dao.dto.BookReadDTO;
import pl.rscorporation.bookstoreapi.dao.dto.BookWriteDTO;
import pl.rscorporation.bookstoreapi.manager.BookService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {

    private BookService bookService;
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

//    @GetMapping(params = {"!size", "!page", "!sort"})
//    public ResponseEntity<List<BookReadDTO>> getBooks() {
//        logger.info("Custom pageable");
//        return ResponseEntity.ok(bookService.findAll());
//    }

    @ApiOperation(value = "Get all books")
    @GetMapping
    public ResponseEntity<List<BookReadDTO>> getBooks() {
        logger.info("Get all books");
        return ResponseEntity.ok(bookService.findAll());
    }

    @ApiOperation(value = "Get book by identification")
    @GetMapping("/{id}")
    public ResponseEntity<BookReadDTO> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.findBookById(id));
    }

    @ApiOperation(value = "Add new book to database")
    @PostMapping
    public ResponseEntity<BookReadDTO> addBook(@RequestBody BookWriteDTO book) {
        BookReadDTO created = bookService.addBook(book);
        return ResponseEntity.created(URI.create("/" + created.getId())).body(created);
    }


    //?
    @ApiOperation(value = "Update book")
    @PutMapping
    public BookReadDTO updateBook(@RequestBody BookWriteDTO book) {
        return bookService.addBook(book);
    }

    @ApiOperation(value = "Delete book by identification")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable Long id) {
        bookService.deleteBookById(id);
        logger.warn("Book with id: " + id + "was deleted");
        return ResponseEntity.noContent().build();
    }

}
