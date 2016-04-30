package uk.co.pasquotto.p2pMortgage.mortgage.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
		
		//HttpHeaders headers = template.getForEntity(url + "/mortgage", String.class).getHeaders();
		//assertThat(headers.getLocation().toString(), containsString("myotherhost"));
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
		assertEquals(principal, mortgage.getPrincipal(), 0.001D);
		assertEquals(interest, mortgage.getInterest(), 0.001D);
	}
	
	

}
