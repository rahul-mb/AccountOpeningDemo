package org.learn.AccountOpeningDemo.controller;

import org.learn.AccountOpeningDemo.Exception.CustomerNotFoundException;
import org.learn.AccountOpeningDemo.model.OpenCurrentAccountRequest;
import org.learn.AccountOpeningDemo.model.OpenCurrentAccountResponse;
import org.learn.AccountOpeningDemo.service.OpenAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * Controller for the operations related to CurrentAccount for a customer
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    /** instance of OpenAccountService     */
    private OpenAccountService openAccountService;

    /**
     * constructor of the controller
     * @param openAccountService
     */
    @Autowired
    AccountController(final OpenAccountService openAccountService){
        this.openAccountService = openAccountService;
    }

    /**
     * Operation to open current account
     *
     * @param openCurrentAccountRequest
     * @return instance of OpenCurrentAccountResponse
     * @throws CustomerNotFoundException
     */
    @PostMapping(value = "/currentbankaccount", consumes = "application/json" ,produces = "application/json")
    public ResponseEntity<OpenCurrentAccountResponse> openCurrentAccount(@Valid @RequestBody final OpenCurrentAccountRequest openCurrentAccountRequest) throws CustomerNotFoundException {
        return new ResponseEntity<>(openAccountService.openCurrentAccount(openCurrentAccountRequest), HttpStatus.CREATED);
    }

}
