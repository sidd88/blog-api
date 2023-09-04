package sidd88.blogapi.service;

import sidd88.blogapi.payload.CommentDto;

import java.util.List;

/**
 * Service: Comment
 */
public interface CommentService {
  public CommentDto createComment(long postId, CommentDto commentDto);

  List<CommentDto> getCommentsByPostId(long postId);

  CommentDto getCommentById(Long postId, Long commentId);

  CommentDto updateComment(Long postId, long commentId, CommentDto commentRequest);

  void deleteComment(Long postId, Long commentId);
}
