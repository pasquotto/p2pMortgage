/**
 * 
 */
package uk.co.pasquotto.p2pMortgage;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.dozer.DozerBeanMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.co.pasquotto.p2pMortgage.mortgage.model.Investment;
import uk.co.pasquotto.p2pMortgage.mortgage.model.InvestmentEntity;
import uk.co.pasquotto.p2pMortgage.mortgage.model.Lender;
import uk.co.pasquotto.p2pMortgage.mortgage.model.Mortgage;
import uk.co.pasquotto.p2pMortgage.mortgage.model.MortgageEntity;

/**
 * 
 * To make sure that dozer mappings are working as expected
 * @author Rafael Costa
 *
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = P2pMortgageApplication.class)
public class MortgageMappingTest {

	//@Inject
	private DozerBeanMapper mapper;

	@Before
	public void setUp() {
		mapper = new DozerBeanMapper();
		mapper.addMapping(new MortgageMappingBuilder());
	}
	
	@Test
	public void testMortgageToMortgageEntity() {
		
		Mortgage mortgage = new Mortgage("Mortgage1", 230000D, 0.5D);
		
		mortgage.addInvestment(new Investment(123000D, new Lender("Lender1")));
		mortgage.addInvestment(new Investment(103000D, new Lender("Lender2")));
		
		MortgageEntity entity = mapper.map(mortgage, MortgageEntity.class);
		
		assertEquals(mortgage.getName(), entity.getName());
		assertEquals(mortgage.getPrincipal(), entity.getPrincipal(), 0.001D);
		assertEquals(mortgage.getInterest(), entity.getInterest(), 0.001D);
		
		List<InvestmentEntity> investments = entity.getInvestments();
		assertEquals(123000D, investments.get(0).getAmmount(), 0.001D);
		assertEquals("Lender1", investments.get(0).getLender());
		assertEquals(103000D, investments.get(1).getAmmount(), 0.001D);
		assertEquals("Lender2", investments.get(1).getLender());
	}

	@Test
	public void TestMortgageEntityToMortgage() {
		MortgageEntity entity = new MortgageEntity("Mortgage1");
		entity.setPrincipal(345678D);
		entity.setInterest(0.46D);
		
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
		
		Mortgage mortgage = mapper.map(entity, Mortgage.class);
		
		assertEquals(entity.getName(), mortgage.getName());
		List<Investment> investments = mortgage.getInvestments();
		assertEquals(154234D, investments.get(0).getAmmount(), 0.00D);
		assertEquals("Lender 1", investments.get(0).getLender().getName());
		assertEquals(145634D, investments.get(1).getAmmount(), 0.00D);
		assertEquals("Lender 2", investments.get(1).getLender().getName());
	}
	
}
