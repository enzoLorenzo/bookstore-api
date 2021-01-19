package pl.rscorporation.bookstoreapi.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.rscorporation.bookstoreapi.dao.UserRepository;
import pl.rscorporation.bookstoreapi.dao.models.User;

import java.util.Optional;

interface SqlUserRepository extends JpaRepository <User, Long>, UserRepository {
}
