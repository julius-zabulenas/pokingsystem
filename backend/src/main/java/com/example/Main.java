package com.example;

import com.example.models.Poke;
import com.example.models.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@SpringBootApplication
public class Main implements RepositoryRestConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		config.exposeIdsFor(User.class);
		config.exposeIdsFor(Poke.class);
	}
}

//	You did not fully complete the tatorial of Spring, cuz no token expiration?
//	Add some tests XD? Make em look good. Baeldung on rest relationships has tests
// Make sure to enable security on some endpoints
// Make images added to the file server always unique
// Add keys to poked by
// Mobile usability???