package sidd88.blogapi.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sidd88.blogapi.entity.Role;
import sidd88.blogapi.entity.User;
import sidd88.blogapi.exception.BlogAPIException;
import sidd88.blogapi.payload.LoginDto;
import sidd88.blogapi.payload.RegisterDto;
import sidd88.blogapi.repository.RoleRepository;
import sidd88.blogapi.repository.UserRepository;
import sidd88.blogapi.security.JwtTokenProvider;
import sidd88.blogapi.service.AuthService;

import java.util.HashSet;
import java.util.Set;

/**
 * Service implementation: Authentication
 */
@Service
public class AuthServiceImpl implements AuthService {

  private AuthenticationManager authenticationManager;
  private UserRepository        userRepository;
  private RoleRepository        roleRepository;
  private PasswordEncoder       passwordEncoder;
  private JwtTokenProvider      tokenProvider;

  AuthServiceImpl(
    AuthenticationManager authenticationManager,
    UserRepository userRepository,
    RoleRepository roleRepository,
    PasswordEncoder passwordEncoder,
    JwtTokenProvider tokenProvider
  ) {
    this.authenticationManager = authenticationManager;
    this.userRepository        = userRepository;
    this.roleRepository        = roleRepository;
    this.passwordEncoder       = passwordEncoder;
    this.tokenProvider         = tokenProvider;
  }

  @Override
  public String login(LoginDto loginDto) {
    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword())
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);

    String token = tokenProvider.generateToken(authentication);
    return token;
  }

  @Override
  public String register(RegisterDto registerDto) {
    if (userRepository.existsByUsername(registerDto.getUsername())) {
      throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Username is already exists!.");
    }

    if (userRepository.existsByEmail(registerDto.getEmail())) {
      throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Email is already exists!.");
    }

    User user = new User();
    user.setName(registerDto.getName());
    user.setUsername(registerDto.getUsername());
    user.setEmail(registerDto.getEmail());
    user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

    Set<Role> roles = new HashSet<>();
    Role userRole = roleRepository.findByName("ROLE_USER").get();
    roles.add(userRole);

    user.setRoles(roles);

    userRepository.save(user);

    return "User registered successfully!.";
  }
}
