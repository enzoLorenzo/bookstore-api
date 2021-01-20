package pl.rscorporation.bookstoreapi.dao.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.rscorporation.bookstoreapi.dao.dto.AuthorWriteDTO;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "author")
public class Author {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String firstName;
    private String surname;
    private String country;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "author", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Book> books;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Author updateAuthor(AuthorWriteDTO author){
        this.id = author.getId();
        this.country = author.getCountry();
        this.surname = author.getSurname();
        this.firstName = author.getFirstName();
        return this;
    }
}
