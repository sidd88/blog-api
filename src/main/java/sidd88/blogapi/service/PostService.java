package sidd88.blogapi.service;

import sidd88.blogapi.payload.PostDto;
import sidd88.blogapi.payload.PostResponse;

/**
 * Service: Post
 */
public interface PostService {
  PostDto create(PostDto post);

  PostResponse getAll(int pageNo, int pageSize, String sortBy, String sortDir);

  PostDto get(long id);

  PostDto update(PostDto postDto, long id);

  void delete(long id);
}
