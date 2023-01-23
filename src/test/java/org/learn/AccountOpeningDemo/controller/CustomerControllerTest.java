package org.learn.AccountOpeningDemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.learn.AccountOpeningDemo.Exception.CustomerNotFoundException;
import org.learn.AccountOpeningDemo.dao.CustomerRepository;
import org.learn.AccountOpeningDemo.entity.Customer;
import org.learn.AccountOpeningDemo.model.OpenCurrentAccountRequest;
import org.learn.AccountOpeningDemo.service.RetrieveCustomerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private CustomerController customerController;

    @MockBean
    private RetrieveCustomerInfoService retrieveCustomerInfoService;

    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private MockMvc mvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void checkIfCustomerDoesNotExist() throws Exception {
        when(retrieveCustomerInfoService.retrieveCustomerInfo(anyLong())).thenThrow(new CustomerNotFoundException("No customer with customer ID: " + "1"));

        mvc.perform(MockMvcRequestBuilders
                        .get("/customer/customerinformation/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
