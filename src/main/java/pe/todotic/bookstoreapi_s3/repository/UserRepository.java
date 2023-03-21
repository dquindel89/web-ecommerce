package pe.todotic.bookstoreapi_s3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.todotic.bookstoreapi_s3.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
