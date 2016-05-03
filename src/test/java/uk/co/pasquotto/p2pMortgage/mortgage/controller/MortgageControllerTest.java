package uk.co.pasquotto.p2pMortgage.mortgage.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import uk.co.pasquotto.p2pMortgage.P2pMortgageApplication;
import uk.co.pasquotto.p2pMortgage.mortgage.model.Investment;
import uk.co.pasquotto.p2pMortgage.mortgage.model.Lender;
import uk.co.pasquotto.p2pMortgage.mortgage.model.Mortgage;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = P2pMortgageApplication.class)
@WebIntegrationTest(randomPort = true)
public class MortgageControllerTest {

	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	private RestTemplate template = new TestRestTemplate();

	private String host = "localhost";
	
	private String url;
	
	@Value("${local.server.port}")
    int port;
	
	@Before
	public void setUp() {
		this.url = "http://" + host + ":" + port;
	}
	
	@Test
	public void testGetMortgage() {
		ResponseEntity<Mortgage> mortgage = template.getForEntity(url + "/mortgage/2", Mortgage.class);
		
		assertNotNull(mortgage);
		assertEquals("Second Mortgage", mortgage.getBody().getName());
		assertEquals(HttpStatus.OK, mortgage.getStatusCode());
		
	}
	
	@Test
	public void testCreateMortgage() {
		String name = "Mortgage1";
		double principal = 1000D;
		double interest = 0.5D;
		Mortgage mortgage = new Mortgage(name, principal, interest);
		ResponseEntity<Mortgage> postForEntity = template.postForEntity(url + "/mortgage", mortgage, Mortgage.class);
		assertEquals(HttpStatus.CREATED, postForEntity.getStatusCode());
		Mortgage createdMortgage = postForEntity.getBody();
		assertNotNull(createdMortgage.getId());
		assertEquals(name, createdMortgage.getName());
		assertEquals(principal, createdMortgage.getPrincipal(), 0.001D);
		assertEquals(interest, createdMortgage.getInterest(), 0.001D);
	}
	
	@Test
	public void testMortgageNotFound() {
		ResponseEntity<Mortgage> mortgage = template.getForEntity(url + "/mortgage/134321", Mortgage.class);
		assertNotNull(mortgage);
		assertEquals(HttpStatus.NOT_FOUND, mortgage.getStatusCode());
	}
	
	@Test
	public void testDeleteLoan() {
		template.delete(url + "/mortgage/1");
		ResponseEntity<Mortgage> mortgage = template.getForEntity(url + "/mortgage/1", Mortgage.class);
		
		assertNotNull(mortgage);
		assertEquals(HttpStatus.NOT_FOUND, mortgage.getStatusCode());
	}
	
	@Test
	public void testInvestIntoMortgageBiggerThanAllowed() {
		Investment investment = new Investment(50000D, new Lender("lender"));
		ResponseEntity<Investment> response  = template.postForEntity(url + "/mortgage/3/portfolio", investment, Investment.class);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}
	
	@Test
	public void testInvestIntoMortgage() {
		Investment investment = new Investment(500D, new Lender("lender"));
		ResponseEntity<Investment> response  = template.postForEntity(url + "/mortgage/3/portfolio", investment, Investment.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		
		ResponseEntity<Mortgage> responseMortgage = template.getForEntity(url + "/mortgage/3", Mortgage.class);
		
		Mortgage mortgage = responseMortgage.getBody();
		List<Investment> investmentsFromLender = mortgage.getPortfolio().getInvestmentsFromLender(new Lender("lender"));
		assertEquals(1, investmentsFromLender.size());
		Investment actual = investmentsFromLender.get(0);
		assertEquals(investment.getAmmount(), actual.getAmmount(), 0.001D);
		assertEquals(investment.getLender().getName(), actual.getLender().getName());
	}

}
