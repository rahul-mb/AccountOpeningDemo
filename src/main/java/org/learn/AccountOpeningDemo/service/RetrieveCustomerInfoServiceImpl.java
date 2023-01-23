package org.learn.AccountOpeningDemo.service;

import org.learn.AccountOpeningDemo.Exception.CustomerNotFoundException;
import org.learn.AccountOpeningDemo.dao.CustomerRepository;
import org.learn.AccountOpeningDemo.entity.AccountTransaction;
import org.learn.AccountOpeningDemo.entity.Customer;
import org.learn.AccountOpeningDemo.entity.CustomerAccount;
import org.learn.AccountOpeningDemo.model.Account;
import org.learn.AccountOpeningDemo.model.CustomerInformationResponse;
import org.learn.AccountOpeningDemo.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation for the interface RetrieveCustomerInfoService
 */
@Service
@Transactional
public class RetrieveCustomerInfoServiceImpl implements RetrieveCustomerInfoService {

    /**  * instance of Repository for table customer    */
    private CustomerRepository customerRepository;

    /**
     * Constructor for the class RetrieveCustomerInfoServiceImpl
     * @param customerRepository
     */
    @Autowired
    public RetrieveCustomerInfoServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    /**
     * Method which retreives the customer information which contains account and their respective transactions
     * @param customerId
     * @return instance of CustomerInformationResponse
     * @throws CustomerNotFoundException
     */
    @Override
    public CustomerInformationResponse retrieveCustomerInfo(Long customerId) throws CustomerNotFoundException {
        CustomerInformationResponse customerInformationResponse = new CustomerInformationResponse();
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent()){
            setCustomerInfo(customer.get(), customerInformationResponse);
        }else {
            throw new CustomerNotFoundException("No customer with customer ID: " + customerId);
        }
        return customerInformationResponse;
    }

    /**
     * Method which formats the customerInformation before returning the response.
     * @param customer
     * @param customerInformationResponse
     */
    private void setCustomerInfo(Customer customer, CustomerInformationResponse customerInformationResponse){
        customerInformationResponse.setCustomerId(customer.getCustomerId());
        if(null != customer.getCustomerName()){
            customerInformationResponse.setCustomerName(customer.getCustomerName());
        }
        if(null != customer.getSurname()){
            customerInformationResponse.setSurname(customer.getSurname());
        }

        List<Account> accountList = new ArrayList<>();
        if(null != customer.getCustomerAccounts() && !customer.getCustomerAccounts().isEmpty()){
            for(CustomerAccount customerAccount : customer.getCustomerAccounts()){
                Account account = new Account();
                account.setAccountId(customerAccount.getAccountId());
                List<Transaction> transactions = new ArrayList<>();
                if(null != customerAccount.getAccountTransactions() && !customerAccount.getAccountTransactions().isEmpty()){
                    for(AccountTransaction accountTransaction : customerAccount.getAccountTransactions()){
                        Transaction transaction = new Transaction();
                        transaction.setTransactionId(accountTransaction.getTransactionId());
                        transaction.setTransactionType(accountTransaction.getTransactionType());
                        transaction.setAmount(accountTransaction.getAmount());
                        transactions.add(transaction);
                    }
                }
                account.setTransactionList(transactions);
                accountList.add(account);
            }
        }
        customerInformationResponse.setAccountList(accountList);
    }
}
