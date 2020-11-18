package pl.rscorporation.bookstoreapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.rscorporation.bookstoreapi.dao.AuthorRepository;
import pl.rscorporation.bookstoreapi.dao.dto.AuthorReadDTO;
import pl.rscorporation.bookstoreapi.dao.dto.AuthorWriteDTO;
import pl.rscorporation.bookstoreapi.dao.dto.BookReadDTO;
import pl.rscorporation.bookstoreapi.RestNames;
import pl.rscorporation.bookstoreapi.dao.models.Author;
import pl.rscorporation.bookstoreapi.manager.AuthorService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(RestNames.AUTHORS_PATH)
public class AuthorController {

    private AuthorService authorService;
    private static final Logger logger = LoggerFactory.getLogger(AuthorController.class);

    public AuthorController(AuthorService authorService, AuthorRepository authorRepository) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<AuthorReadDTO>> getAuthors() {
        logger.info("Get all authors");
        return ResponseEntity.ok(authorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorReadDTO> getAuthorById(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.findAuthorById(id));
    }

    @GetMapping("/{authorId}/books")
    public ResponseEntity<List<BookReadDTO>> getAuthorBooks(@PathVariable Long authorId){
        logger.info("Got all author books");
        return ResponseEntity.ok(authorService.findAuthorBooks(authorId));
    }

    //?
    @GetMapping("/{country}")
    public Iterable<Author> getAuthorsByCountry(@PathVariable String country) {
        return authorService.findByCountry(country);
    }


    @PostMapping
    public ResponseEntity<AuthorReadDTO> addAuthor(@RequestBody AuthorWriteDTO author){
        AuthorReadDTO saved = authorService.saveAuthor(author);
        logger.info("Author added");
        return ResponseEntity.created(URI.create("/" + saved.getId())).body(saved);
    }

    //?
    @PutMapping
    public AuthorReadDTO updateAuthor(@RequestBody AuthorWriteDTO author){
        logger.info("Author updated");
        return authorService.saveAuthor(author);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthorById(@PathVariable Long id) {
        authorService.deleteAuthorById(id);
        logger.warn("Author with id: " + id + "was deleted");
        return ResponseEntity.noContent().build();
    }


}
