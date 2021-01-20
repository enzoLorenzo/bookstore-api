package pl.rscorporation.bookstoreapi.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.rscorporation.bookstoreapi.dao.models.Author;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorWriteDTO {
    private long id;
    private String firstName;
    private String surname;
    private String country;

    public Author createAuthor(){
        Author authorToReturn = new Author();
        authorToReturn.setId(id);
        authorToReturn.setCountry(country);
        authorToReturn.setFirstName(firstName);
        authorToReturn.setSurname(surname);
        return authorToReturn;
    }
}
