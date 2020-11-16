package pl.rscorporation.bookstoreapi.dao.dto;


import lombok.Getter;
import lombok.Setter;
import pl.rscorporation.bookstoreapi.dao.models.Author;
import pl.rscorporation.bookstoreapi.dao.models.Book;


import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class AuthorReadDTO {
    private String firstName;
    private String surname;
    private String country;
    private Set<BookReadDTO> books;

    public AuthorReadDTO(Author author){
        this.firstName = author.getFirstName();
        this.surname = author.getSurname();
        this.country = author.getCountry();
        this.books = author.getBooks().stream()
                .map(book -> new BookReadDTO(book))
                .collect(Collectors.toSet());
    }
}
