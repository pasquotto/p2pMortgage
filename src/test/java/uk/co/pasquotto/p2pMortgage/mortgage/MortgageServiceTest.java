package uk.co.pasquotto.p2pMortgage.mortgage;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import javax.inject.Inject;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.SpringApplicationConfiguration;

import uk.co.pasquotto.p2pMortgage.P2pMortgageApplication;
import uk.co.pasquotto.p2pMortgage.mortgage.model.Mortgage;
import uk.co.pasquotto.p2pMortgage.mortgage.model.MortgageEntity;
import uk.co.pasquotto.p2pMortgage.mortgage.repository.MortgageRepository;
import uk.co.pasquotto.p2pMortgage.mortgage.service.MortgageServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration(classes = P2pMortgageApplication.class)
public class MortgageServiceTest {

	@Mock
	MortgageRepository mortgageRepository;
	
	@Mock
	Mapper mapper;
	
	private final Mapper realMapper = new DozerBeanMapper();
	
	@InjectMocks
	private MortgageServiceImpl mortgageService;
	
	@Test
	public void test() {
		
		when(mapper.map(any(), eq(MortgageEntity.class))).thenAnswer(new Answer<MortgageEntity>() {
			@Override
			public MortgageEntity answer(InvocationOnMock invocation) throws Throwable {
				Object[] args = invocation.getArguments();
				return realMapper.map(args[0], MortgageEntity.class);
			}
		});

		when(mapper.map(any(), eq(Mortgage.class))).thenAnswer(new Answer<Mortgage>() {
			@Override
			public Mortgage answer(InvocationOnMock invocation) throws Throwable {
				Object[] args = invocation.getArguments();
				return realMapper.map(args[0], Mortgage.class);
			}
		});

		
		when(mortgageRepository.save((MortgageEntity)any())).thenAnswer(new Answer<MortgageEntity>() {
			@Override
			public MortgageEntity answer(InvocationOnMock invocation) throws Throwable {
				Object[] args = invocation.getArguments();
				MortgageEntity mortgage = (MortgageEntity)args[0];
				mortgage.setId(23);
				return mortgage;
			}
		});
		
		Mortgage mortgage = new Mortgage("Mortgage", 100D,0.5D);
		Mortgage createdMortgage = mortgageService.createMortgage(mortgage);
		assertNotNull(createdMortgage);
		assertThat(createdMortgage.getId(), greaterThan(0));
	}

}
