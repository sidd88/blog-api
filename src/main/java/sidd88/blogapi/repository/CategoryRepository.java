package sidd88.blogapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sidd88.blogapi.entity.Category;

/**
 * Repository: Category
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
