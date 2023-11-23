package umc5th.spring;

import javax.persistence.Id;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import umc5th.spring.domain.Member;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}
}
