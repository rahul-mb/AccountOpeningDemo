package org.learn.AccountOpeningDemo.service;

import org.learn.AccountOpeningDemo.Exception.CustomerNotFoundException;
import org.learn.AccountOpeningDemo.dao.AccountTransactionRepository;
import org.learn.AccountOpeningDemo.dao.CustomerAccountRepository;
import org.learn.AccountOpeningDemo.dao.CustomerRepository;
import org.learn.AccountOpeningDemo.entity.AccountTransaction;
import org.learn.AccountOpeningDemo.entity.Customer;
import org.learn.AccountOpeningDemo.entity.CustomerAccount;
import org.learn.AccountOpeningDemo.model.OpenCurrentAccountRequest;
import org.learn.AccountOpeningDemo.model.OpenCurrentAccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

/**
 * Implementation for the service OpenAccountService which is used to open a current account
 */
@Service
@Transactional
public class OpenAccountServiceImpl implements OpenAccountService{

    /**  * instance of Repository for table customer     */
    private CustomerRepository customerRepository;

    /**  * instance of Repository for table customer_account     */
    private CustomerAccountRepository customerAccountRepository;

    /**  * instance of Repository for table account_transaction     */
    private AccountTransactionRepository accountTransactionRepository;

    /**
     * Constructor for the OpenAccountServiceImpl
     * @param customerRepository
     * @param customerAccountRepository
     * @param accountTransactionRepository
     */
    @Autowired
    OpenAccountServiceImpl(final CustomerRepository customerRepository, final CustomerAccountRepository customerAccountRepository, final AccountTransactionRepository accountTransactionRepository ){
        this.accountTransactionRepository = accountTransactionRepository;
        this.customerAccountRepository = customerAccountRepository;
        this.customerRepository = customerRepository;
    }

    /**
     * Method which opens a current account and does the initial credit transaction for input customer/request
     * @param openCurrentAccountRequest
     * @return instance of OpenCurrentAccountResponse
     * @throws CustomerNotFoundException
     */
    @Override
    public OpenCurrentAccountResponse openCurrentAccount(OpenCurrentAccountRequest openCurrentAccountRequest) throws CustomerNotFoundException {
        Optional<Customer> customer = customerRepository.findById(openCurrentAccountRequest.getCustomerId());

        if (!customer.isPresent()){
            throw new CustomerNotFoundException("No customer with customer ID: " + openCurrentAccountRequest.getCustomerId());
        }

        CustomerAccount customerAccount = new CustomerAccount();
        customerAccount.setCustomer(customer.get());
        customerAccount = customerAccountRepository.save(customerAccount);

        AccountTransaction accountTransaction = new AccountTransaction();
        if(null != openCurrentAccountRequest.getInitialCredit() && !(openCurrentAccountRequest.getInitialCredit().compareTo(BigDecimal.ZERO) == 0)) {
            accountTransaction.setCustomerAccount(customerAccount);
            accountTransaction.setTransactionType("credit");
            accountTransaction.setAmount(openCurrentAccountRequest.getInitialCredit());
            accountTransaction = accountTransactionRepository.save(accountTransaction);
        }

        return new OpenCurrentAccountResponse(customer.get().getCustomerId(),customerAccount.getAccountId(), accountTransaction.getTransactionId());
    }
}
