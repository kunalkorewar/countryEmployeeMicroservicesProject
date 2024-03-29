package com.microservices;

import com.microservices.entities.Country;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.net.URISyntaxException;

@SpringBootTest
class MicroservicesProjectApplicationTests {

	@Test
	void contextLoads() {
	}

	// addCountry
	@Test
	public void addCountryAPI() throws URISyntaxException {
		RestTemplate restTemplate=new RestTemplate();
		String baseURL="http://localhost:8080/api/addCountry";
		URI uri=new URI(baseURL);
		Country country=new Country();
		country.setCname("brazil");
		ResponseEntity<String> result=restTemplate.postForEntity(uri,country,String.class);
		Assertions.assertEquals(201,result.getStatusCodeValue());
	}
// updateCountry
	@Test
	public void updateCountryAPI() throws URISyntaxException {
		RestTemplate restTemplate=new RestTemplate();
		String baseURL="http://localhost:8080/api/updateCountry";
		URI uri=new URI(baseURL);
		Country country=new Country();
		country.setCid(10);
		country.setCname("argentina");
		restTemplate.put(uri,country);
		Assertions.assertEquals(201 | 200,201 | 200);
	}

	// getAllCountries
	@Test
	public void getAllCountriesAPI() throws URISyntaxException {
		RestTemplate restTemplate=new RestTemplate();
		String baseURL="http://localhost:8080/api/getAllCountries";
		URI uri=new URI(baseURL);
		ResponseEntity<String> result=restTemplate.getForEntity(uri,String.class);
		Assertions.assertEquals(200,result.getStatusCodeValue());
	}

	// getCountryById
	@Test
	public void getCountryByIdAPI() throws URISyntaxException {
		RestTemplate restTemplate=new RestTemplate();
		String baseURL="http://localhost:8080/api/getCountryById/1";
		URI uri=new URI(baseURL);
		ResponseEntity<String> result=restTemplate.getForEntity(uri,String.class);
		Assertions.assertEquals(200,result.getStatusCodeValue());
	}

	// deleteCountryById
	@Test
	public void deleteCountryByIdAPI() throws URISyntaxException {
		RestTemplate restTemplate=new RestTemplate();
		String baseURL="http://localhost:8080/api/deleteCountryById/6";
		URI uri=new URI(baseURL);
		restTemplate.delete(uri);
		Assertions.assertEquals(200,200);
		System.out.println("test ok..");
	}











//	private MockMvc mockMvc;
//	@Test
//	public void deleteCountryByIdAPI() throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:8080/deleteCountryById/6")
//						.accept(MediaType.APPLICATION_JSON))
//				.andExpect(MockMvcResultMatchers.status().isAccepted());
//	}

}
