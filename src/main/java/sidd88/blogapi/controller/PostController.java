package sidd88.blogapi.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sidd88.blogapi.payload.PostDto;
import sidd88.blogapi.payload.PostResponse;
import sidd88.blogapi.service.PostService;

/**
 * Controller: Post
 */
@RestController
@RequestMapping("/api/posts")
public class PostController {

  private PostService postService;

  public PostController(PostService postService) {
    this.postService = postService;
  }

  /**
   * Create blog post
   */
  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping
  public ResponseEntity<PostDto> createPost(
    @Valid @RequestBody PostDto postDto
  ) {
    return new ResponseEntity<>(postService.create(postDto), HttpStatus.CREATED);
  }

  /**
   * Get all post REST api
   */
  @GetMapping
  public PostResponse getAllPosts(
    @RequestParam(value = "pageNo",   defaultValue = "0",   required = false) int    pageNo,
    @RequestParam(value = "pageSize", defaultValue = "10",  required = false) int    pageSize,
    @RequestParam(value = "sortBy",   defaultValue = "id",  required = false) String sortBy,
    @RequestParam(value = "sortDir",  defaultValue = "asc", required = false) String sortDir
  ) {
    return postService.getAll(pageNo, pageSize, sortBy, sortDir);
  }

  /**
   * Get post by id REST api
   */
  @GetMapping("/{id}")
  public ResponseEntity<PostDto> getPost(@PathVariable(name = "id") long id) {
    return ResponseEntity.ok(postService.get(id));
  }

  /**
   * Update existing Post REST api
   */
  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("/{id}")
  public ResponseEntity<PostDto> updatePost(
    @Valid @RequestBody PostDto postDto,
    @PathVariable(name = "id") long id
  ) {
    return ResponseEntity.ok(postService.update(postDto, id));
  }

  /**
   * Delete Post
   */
  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id) {
    postService.delete(id);
    return ResponseEntity.ok("Post entity delete successfully.");
  }
}
