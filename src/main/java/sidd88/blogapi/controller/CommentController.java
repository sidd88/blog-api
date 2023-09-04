package sidd88.blogapi.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sidd88.blogapi.payload.CommentDto;
import sidd88.blogapi.service.CommentService;

import java.util.List;

/**
 * Controller: Comment
 */
@RestController
@RequestMapping("/api")
public class CommentController {

  public CommentService commentService;

  public CommentController(CommentService commentService) {
    this.commentService = commentService;
  }

  @PostMapping("/posts/{postId}/comments")
  public ResponseEntity<CommentDto> createComment(
    @PathVariable(value = "postId") Long       postId,
    @Valid @RequestBody             CommentDto commentDto
  ) {
    return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
  }

  @GetMapping("/posts/{postId}/comments")
  public List<CommentDto> getCommentsByPostId(
    @PathVariable(value = "postId") Long postId
  ) {
    return commentService.getCommentsByPostId(postId);
  }

  @GetMapping("/posts/{postId}/comments/{id}")
  public ResponseEntity<CommentDto> getCommentById(
    @PathVariable(value = "postId") Long postId,
    @PathVariable(value = "id")     Long commentId
  ) {
    CommentDto commentDto = commentService.getCommentById(postId, commentId);
    return new ResponseEntity<>(commentDto, HttpStatus.OK);
  }

  @PutMapping("/posts/{postId}/comments/{id}")
  public ResponseEntity<CommentDto> updateComment(
    @PathVariable(value = "postId") Long       postId,
    @PathVariable(value = "id")     Long       commentId,
    @Valid @RequestBody             CommentDto commentDto
  ) {
    CommentDto updatedComment = commentService.updateComment(postId, commentId, commentDto);
    return new ResponseEntity<>(updatedComment, HttpStatus.OK);
  }

  @DeleteMapping("/posts/{postId}/comments/{id}")
  public ResponseEntity<String> deleteComment(
    @PathVariable(value = "postId") Long postId,
    @PathVariable(value = "id")     Long commentId
  ) {
    commentService.deleteComment(postId, commentId);
    return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
  }
}