package com.nobrega.poc.customer.application.intregation;

import com.nobrega.poc.customer.application.CustomerRequest;
import com.nobrega.poc.customer.application.CustomerResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerIntegrationTests {

	private static final String DOCUMENT = "document";
	private static final String NAME = "name";
	private static final String NEW_NAME = "NEW_NAME";
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

//	@Test
	void testGetCustomerByUuid() {
		//Arrange
		CustomerRequest request = CustomerRequest
				.builder()
				.document(DOCUMENT)
				.name(NAME)
				.build();

		//Act
		String uri = createCustomer(request);
		CustomerResponse customerResponse = this.restTemplate.getForEntity(uri, CustomerResponse.class).getBody();

		//Assert
		assert customerResponse != null;
		assertThat(customerResponse.getName()).isEqualTo(NAME);
		assertThat(customerResponse.getDocument()).isEqualTo(DOCUMENT);
	}

//	@Test
	void testUpdateCustomer() {
		//Arrange
		CustomerRequest request = CustomerRequest
				.builder()
				.document(DOCUMENT)
				.name(NAME)
				.build();

		CustomerRequest updatedCustomer = CustomerRequest
				.builder()
				.document(DOCUMENT)
				.name(NEW_NAME)
				.build();

		//Act
		String uri = createCustomer(request);
		this.restTemplate.put(uri, updatedCustomer);
		CustomerResponse customerResponse = this.restTemplate.getForEntity(uri, CustomerResponse.class).getBody();

		//Assert
		assert customerResponse != null;
		assertThat(customerResponse.getName()).isEqualTo(NEW_NAME);
		assertThat(customerResponse.getDocument()).isEqualTo(DOCUMENT);
	}

	private String createCustomer(CustomerRequest request) {
		final String baseUrl = "http://localhost:" + port + "/api/v1/customers";
		return Objects.requireNonNull(this.restTemplate
				.postForEntity(baseUrl, request, CustomerResponse.class)
				.getHeaders().getLocation()).toString();
	}

}
