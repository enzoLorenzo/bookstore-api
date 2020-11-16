package pl.rscorporation.bookstoreapi.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.rscorporation.bookstoreapi.dao.AuthorRepository;
import pl.rscorporation.bookstoreapi.dao.models.Author;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void fillDB() {
//        save(new Author(1L, "Szymon", "Lorenc", "Poland"));
//        save(new Author(2L, "Radosław", "Kraj", "Poland"));
//    }

    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    public Iterable<Author> findByCountry(String country) {
        return StreamSupport.stream(authorRepository.findAll().spliterator(), false)
                .filter(element -> element.getCountry().equals(country))
                .collect(Collectors.toList());
    }

    public Iterable<Author> findAll() {
        return authorRepository.findAll();
    }

    public Author save(Author author) {
        return authorRepository.save(author);
    }

    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}
