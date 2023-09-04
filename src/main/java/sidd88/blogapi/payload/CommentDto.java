package sidd88.blogapi.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * DTO: Comment
 */
@Data
public class CommentDto {
  private long   id;

  @NotEmpty(message = "Name should not be null or empty")
  private String name;

  @Email(message = "Email should not be null or empty")
  @Email
  private String email;

  @NotNull
  @Size(min = 10, message = "Comment body must be minimum 10 characters")
  private String body;
}