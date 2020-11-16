package pl.rscorporation.bookstoreapi.manager;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.rscorporation.bookstoreapi.dao.AuthorRepository;
import pl.rscorporation.bookstoreapi.dao.dto.AuthorReadDTO;
import pl.rscorporation.bookstoreapi.dao.dto.AuthorWriteDTO;
import pl.rscorporation.bookstoreapi.dao.models.Author;
import pl.rscorporation.bookstoreapi.dao.models.Book;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public AuthorReadDTO findById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Author with this id not exists"));
        return new AuthorReadDTO(author);
    }

    public Iterable<Author> findByCountry(String country) {
        return StreamSupport.stream(authorRepository.findAll().spliterator(), false)
                .filter(element -> element.getCountry().equals(country))
                .collect(Collectors.toList());
    }

    public List<AuthorReadDTO> findAll() {
        List<Author> authors =  authorRepository.findAll();
        return authors.stream()
                .map(author -> new AuthorReadDTO(author))
                .collect(Collectors.toList());
    }

    public Author save(AuthorWriteDTO author) {
        Author toSave = author.createAuthor();
        return authorRepository.save(toSave);
    }

    public void deleteById(Long id) {
        if(!authorRepository.existsById(id))
            throw new IllegalArgumentException("Author with given id not exists");
        authorRepository.deleteById(id);
    }
}
