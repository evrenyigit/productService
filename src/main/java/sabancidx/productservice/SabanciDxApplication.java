package sabancidx.productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SabanciDxApplication {

	public static void main(String[] args) {
		SpringApplication.run(SabanciDxApplication.class, args);
	}

	}
