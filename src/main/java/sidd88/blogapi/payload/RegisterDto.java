package sidd88.blogapi.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO: Register
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {
  private String name;
  private String username;
  private String email;
  private String password;
}
