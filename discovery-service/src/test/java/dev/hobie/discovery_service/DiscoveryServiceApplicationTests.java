package dev.hobie.discovery_service;

import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class DiscoveryServiceApplicationTests {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	void catalogLoads() {
		var entity = testRestTemplate.getForEntity("/eureka/apps", Map.class);
		Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	void adminLoads() {
		var entity = testRestTemplate.getForEntity("/actuator/env", Map.class);
		Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

}
