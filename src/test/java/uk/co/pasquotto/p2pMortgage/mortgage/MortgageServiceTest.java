package uk.co.pasquotto.p2pMortgage.mortgage;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.co.pasquotto.p2pMortgage.P2pMortgageApplication;
import uk.co.pasquotto.p2pMortgage.mortgage.model.MortgageEntity;
import uk.co.pasquotto.p2pMortgage.mortgage.repository.MortgageRepository;
import uk.co.pasquotto.p2pMortgage.mortgage.service.MortgageService;
import uk.co.pasquotto.p2pMortgage.mortgage.service.MortgageServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration(classes = P2pMortgageApplication.class)
public class MortgageServiceTest {

	@Mock
	MortgageRepository mortgageRepository;
	
	@InjectMocks
	private MortgageServiceImpl mortgageService;
	
	@Test
	public void test() {
		
		when(mortgageRepository.save((MortgageEntity)any())).thenAnswer(new Answer<MortgageEntity>() {
			@Override
			public MortgageEntity answer(InvocationOnMock invocation) throws Throwable {
				Object[] args = invocation.getArguments();
				MortgageEntity mortgage = (MortgageEntity)args[0];
				mortgage.setId(23);
				return mortgage;
			}
		});
		
		MortgageEntity mortgage = new MortgageEntity("Mortgage 1");
		MortgageEntity createdMortgage = mortgageService.createMortgage(mortgage);
		assertNotNull(createdMortgage);
		assertThat(createdMortgage.getId(), greaterThan(0));
	}

}
