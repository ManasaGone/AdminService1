package com.admin;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class AdminServiceApplicationTests {

	@Test
	void contextLoads() {
	}
	 @Bean
	    public RestTemplate restTemplate() {
	        return Mockito.mock(RestTemplate.class);
	    }

}
