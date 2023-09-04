package sidd88.blogapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sidd88.blogapi.entity.User;

import java.util.Optional;

/**
 * Repository: User
 */
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);

  Optional<User> findByUsernameOrEmail(String username, String email);

  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}
