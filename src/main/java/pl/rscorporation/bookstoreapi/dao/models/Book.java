package pl.rscorporation.bookstoreapi.dao.models;

import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "book")
public class Book {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String isbn;
    private String title;
    private BigDecimal price;
    private String currency;
    private LocalDate releaseDate;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;



}
