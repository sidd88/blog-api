package sidd88.blogapi.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import sidd88.blogapi.security.JwtAuthenticationEntryPoint;
import sidd88.blogapi.security.JwtAuthenticationFilter;

/**
 * Security config
 */
@Configuration
@EnableMethodSecurity
@SecurityScheme(
  name = "Bearer Authentication",
  type = SecuritySchemeType.HTTP,
  bearerFormat = "JWT",
  scheme = "bearer"
)
public class SecurityConfig {

  private UserDetailsService userDetailsService;

  private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

  private JwtAuthenticationFilter authenticationFilter;

  public SecurityConfig(
    UserDetailsService userDetailsService,
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
    JwtAuthenticationFilter authenticationFilter
  ) {
    this.userDetailsService = userDetailsService;
    this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    this.authenticationFilter = authenticationFilter;
  }

  @Bean
  public static PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(
    AuthenticationConfiguration configuration
  ) throws Exception {
    return configuration.getAuthenticationManager();
  }

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .csrf().disable()
      .authorizeHttpRequests(authorize ->
        authorize.requestMatchers(HttpMethod.GET, "/api/**").permitAll()
          .requestMatchers("/api/auth/**").permitAll()
          .requestMatchers("/swagger-ui/**").permitAll()
          .requestMatchers("/v3/api-docs/**").permitAll()
          .anyRequest().authenticated()
      ).exceptionHandling(ex -> ex
        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
      ).sessionManagement(session -> session
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      );

    http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
}
