package uk.co.pasquotto.p2pMortgage.mortgage.controller;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import uk.co.pasquotto.p2pMortgage.P2pMortgageApplication;
import uk.co.pasquotto.p2pMortgage.mortgage.model.MortgageEntity;

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
	public void test() {
		ResponseEntity<MortgageEntity> mortgage = template.getForEntity(url + "/mortgage/34", MortgageEntity.class);
		
		assertNotNull(mortgage);
		assertThat(mortgage.getBody().getName(), containsString("34"));
		
		//HttpHeaders headers = template.getForEntity(url + "/mortgage", String.class).getHeaders();
		//assertThat(headers.getLocation().toString(), containsString("myotherhost"));
	}

}
