package uk.co.pasquotto.p2pMortgage;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

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
			
			mortgageRepository.save(new MortgageEntity("first Mortgage") );
			mortgageRepository.save(new MortgageEntity("Second Mortgage") );
			mortgageRepository.save(new MortgageEntity("Third Mortgage") );
			Iterable<MortgageEntity> findAll = mortgageRepository.findAll();
			for (MortgageEntity mortgageEntity : findAll) {
				
				System.out.println(mortgageEntity);
			}
			
			
		};
	}
}
