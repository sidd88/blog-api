package sidd88.blogapi.service;

import sidd88.blogapi.payload.LoginDto;
import sidd88.blogapi.payload.RegisterDto;

/**
 * Service: Authentication
 */
public interface AuthService {

  String login(LoginDto loginDto);

  String register(RegisterDto registerDto);
}
