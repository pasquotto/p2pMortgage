package uk.co.pasquotto.p2pMortgage;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class P2pMortgageApplication {

	public static void main(String[] args) {
		SpringApplication.run(P2pMortgageApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner run() {
		return (args) -> {
			
		};
	}
}
