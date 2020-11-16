package pl.rscorporation.bookstoreapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.rscorporation.bookstoreapi.dao.AuthorRepository;
import pl.rscorporation.bookstoreapi.dao.dto.AuthorReadDTO;
import pl.rscorporation.bookstoreapi.dao.dto.AuthorWriteDTO;
import pl.rscorporation.bookstoreapi.dao.models.Author;
import pl.rscorporation.bookstoreapi.dao.models.Book;
import pl.rscorporation.bookstoreapi.manager.AuthorService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/authors")
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
        return ResponseEntity.ok(authorService.findById(id));
    }

    //?
    @GetMapping("/{country}")
    public Iterable<Author> getAuthorsByCountry(@PathVariable String country) {
        return authorService.findByCountry(country);
    }

    @PostMapping
    public Author addAuthor(@RequestBody AuthorWriteDTO author)    {
        return authorService.save(author);
    }

    //?
    @PutMapping
    public Author updateAuthor(@RequestBody AuthorWriteDTO author)
    {
        logger.info("Author added");
        return authorService.save(author);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthorById(@PathVariable Long id) {
        authorService.deleteById(id);
        logger.warn("Author with id: " + id + "was deleted");
        return ResponseEntity.noContent().build();
    }


}
