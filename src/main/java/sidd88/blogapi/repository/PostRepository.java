package sidd88.blogapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sidd88.blogapi.entity.Post;

/**
 * Repository: Post
 */
public interface PostRepository extends JpaRepository<Post, Long> {
}
