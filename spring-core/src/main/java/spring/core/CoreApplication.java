package spring.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.resilience.annotation.EnableResilientMethods;

@EnableResilientMethods
@SpringBootApplication
public class CoreApplication {

	static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}

}
