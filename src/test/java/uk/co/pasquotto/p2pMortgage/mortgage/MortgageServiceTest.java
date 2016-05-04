package uk.co.pasquotto.p2pMortgage.mortgage;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.SpringApplicationConfiguration;

import uk.co.pasquotto.p2pMortgage.MortgageMappingBuilder;
import uk.co.pasquotto.p2pMortgage.P2pMortgageApplicationTestConfig;
import uk.co.pasquotto.p2pMortgage.mortgage.model.Investment;
import uk.co.pasquotto.p2pMortgage.mortgage.model.Lender;
import uk.co.pasquotto.p2pMortgage.mortgage.model.Mortgage;
import uk.co.pasquotto.p2pMortgage.mortgage.model.MortgageEntity;
import uk.co.pasquotto.p2pMortgage.mortgage.repository.MortgageRepository;
import uk.co.pasquotto.p2pMortgage.mortgage.service.MortgageServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration(classes = P2pMortgageApplicationTestConfig.class)
public class MortgageServiceTest {

	@Mock
	MortgageRepository mortgageRepository;
	
	@Mock
	Mapper mapper;
	
	private DozerBeanMapper realMapper;
	
	@InjectMocks
	private MortgageServiceImpl mortgageService;
	
	@Before
	public void beforeClass() {
		realMapper = new DozerBeanMapper();
		realMapper.addMapping(new MortgageMappingBuilder());
	}
	
	@Test
	public void testCreateMortgage() {
		
		when(mapper.map(any(), eq(MortgageEntity.class))).thenAnswer(mappToMortgageEntityAnswer);
		when(mapper.map(any(), eq(Mortgage.class))).thenAnswer(mappToMortgageAnswer);

		
		when(mortgageRepository.save((MortgageEntity)any())).thenAnswer(saveMortgageAnswer);
		
		Mortgage mortgage = new Mortgage("Mortgage", 100D,0.5D);
		mortgage.addInvestment(new Investment(10D, new Lender("Lender1")));
		mortgage.addInvestment(new Investment(20D, new Lender("Lender2")));
		mortgage.addInvestment(new Investment(30D, new Lender("Lender3")));
		
		Mortgage createdMortgage = mortgageService.createMortgage(mortgage);
		assertNotNull(createdMortgage);
		assertThat(createdMortgage.getId(), greaterThan(0));
		assertEquals(createdMortgage.getInvestments().size(), 3);
		
		List<Investment> investments = mortgage.getPortfolio().getInvestments();
		assertEquals(10D, investments.get(0).getAmmount(), 0.001D);
		assertEquals("Lender1", investments.get(0).getLender().getName());
		assertEquals(20D, investments.get(1).getAmmount(), 0.001D);
		assertEquals("Lender2", investments.get(1).getLender().getName());
		assertEquals(30D, investments.get(2).getAmmount(), 0.001D);
		assertEquals("Lender3", investments.get(2).getLender().getName());
	}

	
	private final Answer<?> mappToMortgageEntityAnswer = invocation -> {
		Object[] args = invocation.getArguments();
		return realMapper.map(args[0], MortgageEntity.class);
	};

	private final Answer<?> mappToMortgageAnswer = invocation -> {
		Object[] args = invocation.getArguments();
		return realMapper.map(args[0], Mortgage.class);
	};
	
	private final Answer<?> saveMortgageAnswer = invocation -> {
		Object[] args = invocation.getArguments();
		MortgageEntity mortgage = (MortgageEntity)args[0];
		mortgage.setId(23);
		return mortgage;
	};
}
