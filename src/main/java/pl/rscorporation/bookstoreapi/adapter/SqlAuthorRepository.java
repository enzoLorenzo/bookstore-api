package pl.rscorporation.bookstoreapi.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rscorporation.bookstoreapi.dao.AuthorRepository;
import pl.rscorporation.bookstoreapi.dao.models.Author;

@Repository
interface SqlAuthorRepository extends AuthorRepository, JpaRepository<Author, Long>{
}
