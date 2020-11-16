package pl.rscorporation.bookstoreapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.rscorporation.bookstoreapi.dao.models.Author;
import pl.rscorporation.bookstoreapi.manager.AuthorService;

import java.util.Optional;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public Iterable<Author> getAuthors() {
        return  authorService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Author> getAuthorById(@PathVariable Long id) {
        return authorService.findById(id);
    }

    @GetMapping("/{country}")
    public Iterable<Author> getAuthorsByCountry(@PathVariable String country) {
        return authorService.findByCountry(country);
    }

    @PostMapping
    public Author addAuthor(@RequestBody Author author)    {
        return authorService.save(author);
    }

    @PutMapping
    public Author updateAuthor(@RequestBody Author author)
    {
        return authorService.save(author);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthorById(@PathVariable Long id) {
        authorService.deleteById(id);
    }


}
