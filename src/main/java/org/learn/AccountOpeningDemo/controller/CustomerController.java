package org.learn.AccountOpeningDemo.controller;

import org.learn.AccountOpeningDemo.Exception.CustomerNotFoundException;
import org.learn.AccountOpeningDemo.entity.Customer;
import org.learn.AccountOpeningDemo.model.CustomerInformationResponse;
import org.learn.AccountOpeningDemo.service.RetrieveCustomerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Operations for an exsting customer of the bank
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    /** instance of RetrieveCustomerInfoService    */
    private RetrieveCustomerInfoService retrieveCustomerInfoService;

    /**
     * Constructor for the CustomerController
     * @param retrieveCustomerInfoService
     */
    public CustomerController(final RetrieveCustomerInfoService retrieveCustomerInfoService){
        this.retrieveCustomerInfoService = retrieveCustomerInfoService;
    }

    /**
     * Operation to retrieve customer information
     * @param customerId
     * @return instance of CustomerInformationResponse
     * @throws CustomerNotFoundException
     */
    @GetMapping("/customerinformation/{customerId}")
    public ResponseEntity<CustomerInformationResponse> getCustomerInfo(@PathVariable final long customerId) throws CustomerNotFoundException {
        return new ResponseEntity<>(retrieveCustomerInfoService.retrieveCustomerInfo(customerId), HttpStatus.OK);
    }

}
