package sidd88.blogapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sidd88.blogapi.entity.Comment;
import sidd88.blogapi.entity.Role;

import java.util.List;
import java.util.Optional;

/**
 * Repository: Role
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

  Optional<Role> findByName(String name);
}
