package org.learn.AccountOpeningDemo.service;

import org.junit.jupiter.api.Test;
import org.learn.AccountOpeningDemo.Exception.CustomerNotFoundException;
import org.learn.AccountOpeningDemo.dao.AccountTransactionRepository;
import org.learn.AccountOpeningDemo.dao.CustomerAccountRepository;
import org.learn.AccountOpeningDemo.dao.CustomerRepository;
import org.learn.AccountOpeningDemo.entity.AccountTransaction;
import org.learn.AccountOpeningDemo.entity.Customer;
import org.learn.AccountOpeningDemo.entity.CustomerAccount;
import org.learn.AccountOpeningDemo.model.CustomerInformationResponse;
import org.learn.AccountOpeningDemo.model.OpenCurrentAccountRequest;
import org.learn.AccountOpeningDemo.model.OpenCurrentAccountResponse;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.validation.constraints.AssertTrue;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RetrieveCustomerInfoServiceTest {

    @Autowired
    public RetrieveCustomerInfoService retrieveCustomerInfoService;

    @Autowired
    public OpenAccountService openAccountService;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private CustomerAccountRepository customerAccountRepository;

    @MockBean
    private AccountTransactionRepository accountTransactionRepository;

    @Test
    public void checkIfExceptionIsThrownWhenInputCustomerDoesNotExist(){
        when(customerRepository.findById(1l)).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class, ()->{retrieveCustomerInfoService.retrieveCustomerInfo(1l);});
    }

    @Test
    public void checkIfBalanceIsZeroInTheResponseInCaseOfNoInitialCredit() throws CustomerNotFoundException {
        when(customerRepository.findById(1l)).thenReturn(setCustomer());
        when(customerAccountRepository.save(any(CustomerAccount.class))).thenReturn(setCustomerAcccountWithoutTransaction());
        OpenCurrentAccountResponse openCurrentAccountResponse = openAccountService.openCurrentAccount(setAccountOpeningRequestWithoutInitialDraft());
        assertEquals(openCurrentAccountResponse.getTransactionId(), 0);
        when(customerRepository.findById(1l)).thenReturn(setCustomerWithAccountWithoutTransaction());
        CustomerInformationResponse customerInformationResponse = retrieveCustomerInfoService.retrieveCustomerInfo(1l);
        assertTrue(customerInformationResponse.getAccountList().get(0).getTransactionList().isEmpty());
    }

    @Test
    public void checkIfBalanceIsZeroInTheResponseInCaseOfInitialCredit() throws CustomerNotFoundException {
        when(customerRepository.findById(1l)).thenReturn(setCustomer());
        when(customerAccountRepository.save(any(CustomerAccount.class))).thenReturn(setCustomerAcccountWithoutTransaction());
        when(accountTransactionRepository.save(any(AccountTransaction.class))).thenReturn(setAccountTransaction());
        OpenCurrentAccountResponse openCurrentAccountResponse = openAccountService.openCurrentAccount(setAccountOpeningRequestWithInitialDraft());
        assertNotEquals(openCurrentAccountResponse.getTransactionId(), 0);
//        when(customerRepository.findById(1l)).thenReturn(setCustomerWithAccountWithTransaction());
        doReturn(setCustomerWithAccountWithTransaction()).when(customerRepository).findById(1l);
        CustomerInformationResponse customerInformationResponse = retrieveCustomerInfoService.retrieveCustomerInfo(1l);
        assertTrue(!customerInformationResponse.getAccountList().get(0).getTransactionList().isEmpty());
    }

    private OpenCurrentAccountRequest setAccountOpeningRequestWithoutInitialDraft() {
        return new OpenCurrentAccountRequest(1l, null);
    }

    private OpenCurrentAccountRequest setAccountOpeningRequestWithInitialDraft() {
        return new OpenCurrentAccountRequest(1l, new BigDecimal(15));
    }

    private Optional<Customer> setCustomer(){
        return Optional.ofNullable(new Customer(1l, "meoww", "cat", null));
    }

    private CustomerAccount setCustomerAcccountWithoutTransaction(){
        return new CustomerAccount(1l,setCustomer().get(),null);
    }

    private AccountTransaction setAccountTransaction(){
        return new AccountTransaction(1l,"credit",new BigDecimal(15),setCustomerAcccountWithoutTransaction());
    }

    private Optional<Customer> setCustomerWithAccountWithoutTransaction(){
        List<CustomerAccount> accountList = new ArrayList<>();
        accountList.add(setCustomerAcccountWithoutTransaction());
        return Optional.ofNullable(new Customer(1l, "meoww", "cat", accountList));
    }

    private Optional<Customer> setCustomerWithAccountWithTransaction(){
        List<CustomerAccount> accountList = new ArrayList<>();
        List<AccountTransaction> transactionList = new ArrayList<>();
        transactionList.add(setAccountTransaction());
        accountList.add(setCustomerAcccountWithoutTransaction());
        accountList.get(0).setAccountTransactions(transactionList);
        return Optional.ofNullable(new Customer(1l, "meoww", "cat", accountList));
    }

}
