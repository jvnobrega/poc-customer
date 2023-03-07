package com.nobrega.poc.customer.application.component;


import com.nobrega.poc.customer.application.CustomerController;
import com.nobrega.poc.customer.application.CustomerResponse;
import com.nobrega.poc.customer.domain.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class MockMvcWithSpringBootTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
    void testGetCustomerByUuid() throws Exception {

        //arrange
        String uuid = UUID.randomUUID().toString();
        var customerResponse = CustomerResponse.builder()
                .name("jv_name")
                .document("document")
                .uuid(uuid)
                .build();

        //act
        when(customerService.getCustomerByUuid(uuid)).thenReturn(customerResponse);

        //assertion
        this.mockMvc.perform(get("/api/v1/customers/" + uuid))
                .andExpect(MockMvcResultMatchers.jsonPath("name").value("jv_name"))
                .andExpect(status().isOk());
    }


}
