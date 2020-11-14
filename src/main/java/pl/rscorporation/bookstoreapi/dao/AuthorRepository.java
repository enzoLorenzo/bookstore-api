package pl.rscorporation.bookstoreapi.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.rscorporation.bookstoreapi.dao.models.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {


}
