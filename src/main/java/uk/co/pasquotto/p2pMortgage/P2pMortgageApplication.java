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
	
	@Bean
	public CommandLineRunner run(MortgageRepository mortgageRepository) {
		return (args) -> {
			
			String name = "first Mortgage";
			double principal = 345678D;
			double interest = 0.46D;
			mortgageRepository.save(createMortgage(name, principal, interest));
			mortgageRepository.save(new MortgageEntity("Second Mortgage") );
			mortgageRepository.save(createMortgage("Third Mortgage", principal, interest));
			Iterable<MortgageEntity> findAll = mortgageRepository.findAll();
			for (MortgageEntity mortgageEntity : findAll) {
				
				System.out.println(mortgageEntity);
			}
			
			
		};
	}

	private MortgageEntity createMortgage(String name, double principal, double interest) {
		MortgageEntity entity = new MortgageEntity(name);
		
		entity.setPrincipal(principal);
		
		entity.setInterest(interest);
		
		List<InvestmentEntity> investmentsEntity = new ArrayList<>();
		InvestmentEntity investmentEntity1 = new InvestmentEntity();
		investmentEntity1.setAmmount(154234D);
		investmentEntity1.setLender("Lender 1");
		investmentsEntity.add(investmentEntity1);
		InvestmentEntity investmentEntity2 = new InvestmentEntity();
		investmentEntity2.setAmmount(145634D);
		investmentEntity2.setLender("Lender 2");
		investmentsEntity.add(investmentEntity2);
		entity.setInvestments(investmentsEntity);
		return entity;
	}
}
