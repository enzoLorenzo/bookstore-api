package pl.rscorporation.bookstoreapi.dao.dto;

import lombok.Getter;
import lombok.Setter;
import pl.rscorporation.bookstoreapi.dao.models.Author;
import pl.rscorporation.bookstoreapi.dao.models.Book;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class BookWriteDTO {
    private String isbn;
    private String title;
    private BigDecimal price;
    private LocalDate releaseDate;
    private long authorId;


    public Book toBook(Author author){
        Book bookToReturn = new Book();
        bookToReturn.setIsbn(this.isbn);
        bookToReturn.setPrice(this.price);
        bookToReturn.setReleaseDate(this.releaseDate);
        bookToReturn.setTitle(this.title);
        bookToReturn.setAuthor(author);

        Set<Book> booksToSet = author.getBooks();
        booksToSet.add(bookToReturn);
        author.setBooks(booksToSet);
        return bookToReturn;
    }
}
