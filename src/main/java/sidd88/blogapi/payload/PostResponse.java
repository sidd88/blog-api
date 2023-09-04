package sidd88.blogapi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO: Post Response
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
  private List<PostDto> content;
  private int           pageNo;
  private int           pageSize;
  private long          totalElements;
  private int           totalPages;
  private boolean       last;
}
