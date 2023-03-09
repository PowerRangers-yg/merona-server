package dku.merona;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MeronaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeronaApplication.class, args);
	}

}
