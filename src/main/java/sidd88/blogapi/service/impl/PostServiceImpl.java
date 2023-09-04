package sidd88.blogapi.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sidd88.blogapi.entity.Post;
import sidd88.blogapi.exception.ResourceNotFoundException;
import sidd88.blogapi.payload.PostDto;
import sidd88.blogapi.payload.PostResponse;
import sidd88.blogapi.repository.PostRepository;
import sidd88.blogapi.service.PostService;

import java.util.List;

/**
 * Service implementation: Post
 */
@Service
public class PostServiceImpl implements PostService {

  private PostRepository postRepository;

  private ModelMapper mapper;

  public PostServiceImpl(
    PostRepository postRepository,
    ModelMapper mapper
  ) {
    this.postRepository = postRepository;
    this.mapper = mapper;
  }

  @Override
  public PostDto create(PostDto postDto) {
    // Convert DTO to Entity
    Post post = mapToEntity(postDto);
    Post newPost = postRepository.save(post);

    // Convert Entity to DTO
    return mapToDTO(newPost);
  }

  @Override
  public PostResponse getAll(int pageNo, int pageSize, String sortBy, String sortDir) {
    Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
    Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

    Page<Post> posts = postRepository.findAll(pageable);
    List<Post> listOfPosts = posts.getContent();
    List<PostDto> content = listOfPosts.stream().map(this::mapToDTO).toList();

    PostResponse response = new PostResponse();
    response.setContent(content);
    response.setPageNo(posts.getNumber());
    response.setPageSize(posts.getSize());
    response.setTotalElements(posts.getTotalElements());
    response.setTotalPages(posts.getTotalPages());
    response.setLast(posts.isLast());

    return response;

  }

  @Override
  public PostDto get(long id) {
    Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
    return mapToDTO(post);
  }

  @Override
  public PostDto update(PostDto postDto, long id) {
    Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
    post.setTitle(postDto.getTitle());
    post.setDescription(postDto.getDescription());
    post.setContent(postDto.getContent());

    Post updatePost = postRepository.save(post);
    return mapToDTO(updatePost);
  }

  @Override
  public void delete(long id) {
    Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
    postRepository.delete(post);
  }

  /**
   * Convert Entity into DTO
   */
  private PostDto mapToDTO(Post post) {
    PostDto postDto = mapper.map(post, PostDto.class);
    return postDto;
  }

  /**
   * Convert DTO into Entity
   */
  private Post mapToEntity(PostDto postDto) {
    Post post = mapper.map(postDto, Post.class);
    return post;
  }
}
