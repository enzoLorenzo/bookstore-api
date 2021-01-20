package pl.rscorporation.bookstoreapi.manager;

import org.springframework.stereotype.Service;
import pl.rscorporation.bookstoreapi.dao.AuthorRepository;
import pl.rscorporation.bookstoreapi.dao.BookRepository;
import pl.rscorporation.bookstoreapi.dao.dto.AuthorReadDTO;
import pl.rscorporation.bookstoreapi.dao.dto.AuthorWriteDTO;
import pl.rscorporation.bookstoreapi.dao.dto.BookReadDTO;
import pl.rscorporation.bookstoreapi.dao.models.Author;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public AuthorReadDTO findAuthorById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Author with this id not exists"));
        return new AuthorReadDTO(author);
    }

    public List<AuthorReadDTO> findAll() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream()
                .map(author -> new AuthorReadDTO(author))
                .collect(Collectors.toList());
    }

    public List<BookReadDTO> findAuthorBooks(Long authorId) {
        if (!authorRepository.existsById(authorId))
            throw new IllegalArgumentException("Author with given id not exists");
        return bookRepository.findByAuthorId(authorId).stream()
                .map(BookReadDTO::new)
                .collect(Collectors.toList());
    }

    public AuthorReadDTO saveAuthor(AuthorWriteDTO author) {
        Author saved = authorRepository.save(author.createAuthor());
        return new AuthorReadDTO(saved);
    }

    public AuthorReadDTO putAuthor(AuthorWriteDTO author, long authorId) {
        if (!authorRepository.existsById(authorId))
            throw new IllegalArgumentException();
        Author saved = authorRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("Author with given id not exists"));
        saved.setId(authorId);
        return new AuthorReadDTO(saved.updateAuthor(author));
    }

    public void deleteAuthorById(Long id) {
        if (!authorRepository.existsById(id))
            throw new IllegalArgumentException("Author with given id not exists");
        authorRepository.deleteById(id);
    }
}
