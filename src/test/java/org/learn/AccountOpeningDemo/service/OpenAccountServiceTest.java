package org.learn.AccountOpeningDemo.service;

import org.junit.jupiter.api.Test;
import org.learn.AccountOpeningDemo.Exception.CustomerNotFoundException;
import org.learn.AccountOpeningDemo.dao.AccountTransactionRepository;
import org.learn.AccountOpeningDemo.dao.CustomerAccountRepository;
import org.learn.AccountOpeningDemo.dao.CustomerRepository;
import org.learn.AccountOpeningDemo.entity.AccountTransaction;
import org.learn.AccountOpeningDemo.entity.Customer;
import org.learn.AccountOpeningDemo.entity.CustomerAccount;
import org.learn.AccountOpeningDemo.model.OpenCurrentAccountRequest;
import org.learn.AccountOpeningDemo.model.OpenCurrentAccountResponse;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
public class OpenAccountServiceTest {

    @Autowired
    private OpenAccountService openAccountService;
    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private CustomerAccountRepository customerAccountRepository;

    @MockBean
    private AccountTransactionRepository accountTransactionRepository;

    @Test
    public void checkIfExceptionIsThrownWhenUserDoesNotExist(){
        when(customerRepository.findById(1l)).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class, ()->{openAccountService.openCurrentAccount(setRequest());});
    }

    @Test
    public void checkIfIntialCreditIsZero() throws CustomerNotFoundException {
        OpenCurrentAccountRequest openCurrentAccountRequest = setRequest();
        openCurrentAccountRequest.setInitialCredit(new BigDecimal(0));
        when(customerRepository.findById(1l)).thenReturn(setCustomerData());
        when(customerAccountRepository.save(any(CustomerAccount.class))).thenReturn(new CustomerAccount(1l,setCustomerData().get(),null));
        OpenCurrentAccountResponse openCurrentAccountResponse = openAccountService.openCurrentAccount(openCurrentAccountRequest);
        assertEquals(openCurrentAccountResponse.getTransactionId(),0l);
    }

    @Test
    public void checkIfIntialCreditIsNotZero() throws CustomerNotFoundException {
        OpenCurrentAccountRequest openCurrentAccountRequest = setRequest();
        openCurrentAccountRequest.setInitialCredit(new BigDecimal(10));
        when(customerRepository.findById(1l)).thenReturn(setCustomerData());
        when(customerAccountRepository.save(any(CustomerAccount.class))).thenReturn(setCustomerAccountData());
        when(accountTransactionRepository.save(any(AccountTransaction.class))).thenReturn(setAccountTransactionData());
        OpenCurrentAccountResponse openCurrentAccountResponse = openAccountService.openCurrentAccount(openCurrentAccountRequest);
        assertEquals(openCurrentAccountResponse.getTransactionId(),1l);
    }

    private OpenCurrentAccountRequest setRequest(){
        return new OpenCurrentAccountRequest(1l, new BigDecimal(100));
    }

    private Optional<Customer> setCustomerData() {
        return Optional.ofNullable(new Customer(1l, "rahul", "bidada", null));
    }

    private CustomerAccount setCustomerAccountData(){
        return new CustomerAccount(1l,setCustomerData().get(),null);
    }

    private AccountTransaction setAccountTransactionData(){
        return new AccountTransaction(1l,"credit",new BigDecimal(10),setCustomerAccountData());
    }
}
