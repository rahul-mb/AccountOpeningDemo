package org.learn.AccountOpeningDemo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.learn.AccountOpeningDemo.Exception.CustomerNotFoundException;
import org.learn.AccountOpeningDemo.dao.CustomerRepository;
import org.learn.AccountOpeningDemo.entity.Customer;
import org.learn.AccountOpeningDemo.model.Account;
import org.learn.AccountOpeningDemo.model.OpenCurrentAccountRequest;
import org.learn.AccountOpeningDemo.model.OpenCurrentAccountResponse;
import org.learn.AccountOpeningDemo.service.OpenAccountService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.math.BigDecimal;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private AccountController accountController;

    @MockBean
    private OpenAccountService openAccountService;

    @MockBean
    private CustomerRepository customerRepository;

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mvc;

//    @Autowired
//    private WebApplicationContext context;

    @Test
    public void testAccountCreationFailure() throws Exception {
        when(openAccountService.openCurrentAccount(any(OpenCurrentAccountRequest.class))).thenThrow(new CustomerNotFoundException("No customer with customer ID: " + "10"));

        mvc.perform(MockMvcRequestBuilders
                .post("/account/currentbankaccount")
                .content(mapper.writeValueAsString(new OpenCurrentAccountRequest(10l,new BigDecimal(10))))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    public void testAccountCreationSuccess() throws Exception {
        when(openAccountService.openCurrentAccount(Mockito.any(OpenCurrentAccountRequest.class))).thenReturn(setOpenCurrentAccountResponse());

        mvc.perform(MockMvcRequestBuilders
                .post("/account/currentbankaccount")
                .content(mapper.writeValueAsString(new OpenCurrentAccountRequest(1,new BigDecimal(10))))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
    }

    private OpenCurrentAccountResponse setOpenCurrentAccountResponse(){
        return new OpenCurrentAccountResponse(1l,1l,1l);
    }

}
