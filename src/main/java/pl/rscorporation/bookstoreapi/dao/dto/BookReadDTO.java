package pl.rscorporation.bookstoreapi.dao.dto;

import lombok.Getter;
import lombok.Setter;
import pl.rscorporation.bookstoreapi.dao.models.Book;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class BookReadDTO {
    private Long id;
    private String title;
    private BigDecimal price;
    private LocalDate releaseDate;

    public BookReadDTO(Book book){
        this.id = book.getId();
        this.title = book.getTitle();
        this.price = book.getPrice();
        this.releaseDate = book.getReleaseDate();
    }
}
