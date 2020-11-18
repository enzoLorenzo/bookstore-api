package pl.rscorporation.bookstoreapi.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.rscorporation.bookstoreapi.dao.AuthorRepository;
import pl.rscorporation.bookstoreapi.dao.models.Author;
import pl.rscorporation.bookstoreapi.dao.models.Book;
import java.util.List;

@Repository
interface SqlAuthorRepository extends AuthorRepository, JpaRepository<Author, Long>{

    @Query("select a from Author a left join fetch a.books")
    List<Author> findAll(@Param("authorId") Long authorId);

}
