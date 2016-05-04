package uk.co.pasquotto.p2pMortgage;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import uk.co.pasquotto.p2pMortgage.mortgage.model.InvestmentEntity;
import uk.co.pasquotto.p2pMortgage.mortgage.model.MortgageEntity;
import uk.co.pasquotto.p2pMortgage.mortgage.repository.MortgageRepository;

/**
 * @author Rafael Costa
 *
 */
@SpringBootApplication
public class P2pMortgageApplication {

	public static void main(String[] args) {
		SpringApplication.run(P2pMortgageApplication.class, args);
	}
	
	@Bean
	public Mapper mapper() {
		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.addMapping(new MortgageMappingBuilder());
		return mapper;
	}
	
	
}
