package sidd88.blogapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "CRUD REST APIs for Post resource")
public class PostController {

  private PostService postService;

  public PostController(PostService postService) {
    this.postService = postService;
  }

  /**
   * Create blog post
   */
  @Operation(
    summary = "Create Post REST API",
    description = "Create Post REST API is used to save post info database"
  )
  @ApiResponse(
    responseCode = "201",
    description = "Http Status 201 CREATED"
  )
  @SecurityRequirement(
    name = "Bearer Authentication"
  )
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
  @Operation(
    summary = "Get All Posts REST API",
    description = "Get All Posts REST API is used to fetch all the posts from the database"
  )
  @ApiResponse(
    responseCode = "200",
    description = "Http Status 200 SUCCESS"
  )
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
  @Operation(
    summary = "Get Post by Id REST API",
    description = "Get Post by Id REST API is used to get single post from the database"
  )
  @ApiResponse(
    responseCode = "200",
    description = "Http Status 200 SUCCESS"
  )
  @SecurityRequirement(
    name = "Bearer Authentication"
  )
  @GetMapping("/{id}")
  public ResponseEntity<PostDto> getPost(@PathVariable(name = "id") long id) {
    return ResponseEntity.ok(postService.get(id));
  }

  /**
   * Update existing Post REST api
   */
  @Operation(
    summary = "Update Post by Id REST API",
    description = "Update Post REST API is used to update a particular post in the database"
  )
  @ApiResponse(
    responseCode = "200",
    description = "Http Status 200 SUCCESS"
  )
  @SecurityRequirement(
    name = "Bearer Authentication"
  )
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
  @Operation(
    summary = "Delete Post REST API",
    description = "Delete Post REST API is used to delete a particular post from the database"
  )
  @ApiResponse(
    responseCode = "200",
    description = "Http Status 200 SUCCESS"
  )
  @SecurityRequirement(
    name = "Bearer Authentication"
  )
  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id) {
    postService.delete(id);
    return ResponseEntity.ok("Post entity delete successfully.");
  }
}
