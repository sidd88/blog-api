package sidd88.blogapi;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Application entry
 */
@SpringBootApplication
@OpenAPIDefinition(
	info = @Info(
		title = "Spring Boot Blog App REST APIs",
		description = "Spring Boot Blog App REST APIS documentation",
		version = "v1.0",
		contact = @Contact(
			name = "Do Dinh Si",
			email = "sidd.hn88@gmail.com",
			url = "https://sidd88.me"
		),
		license = @License(
			name = "Apache 2.0",
			url = "https://sidd88.me/license"
		)
	),
	externalDocs = @ExternalDocumentation(
		description = "Spring Boot Blog App Documentation",
		url = "https://github.com/sidd88/blog-api"
	)
)
public class BlogApiApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(BlogApiApplication.class, args);
	}

}
