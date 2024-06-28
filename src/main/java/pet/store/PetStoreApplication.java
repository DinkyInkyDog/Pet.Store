package pet.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan
public class PetStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetStoreApplication.class, args);
	}

}
