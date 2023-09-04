package sidd88.blogapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sidd88.blogapi.entity.Comment;

import java.util.List;

/**
 * Repository: Comment
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

  List<Comment> findByPostId(long postId);
}
