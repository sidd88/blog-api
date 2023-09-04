package sidd88.blogapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sidd88.blogapi.payload.JWTAuthResponse;
import sidd88.blogapi.payload.LoginDto;
import sidd88.blogapi.payload.RegisterDto;
import sidd88.blogapi.service.AuthService;

/**
 * Controller: Authentication
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping(value = {"/login", "/signin"})
  public ResponseEntity<JWTAuthResponse> login(
    @RequestBody LoginDto loginDto
  ) {
    String token = authService.login(loginDto);
    JWTAuthResponse response = new JWTAuthResponse();
    response.setAccessToken(token);
    return ResponseEntity.ok(response);
  }

  @PostMapping(value = {"/register", "/signup"})
  public ResponseEntity<String> register(
    @RequestBody RegisterDto registerDto
    ) {
    String response = authService.register(registerDto);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }
}
