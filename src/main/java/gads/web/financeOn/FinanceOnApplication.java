package gads.web.financeOn;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinanceOnApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().load();
		System.setProperty("URI_MONGO_ATLASS", dotenv.get("URI_MONGO_ATLASS"));
		SpringApplication.run(FinanceOnApplication.class, args);
	}
// teste new pc
}
