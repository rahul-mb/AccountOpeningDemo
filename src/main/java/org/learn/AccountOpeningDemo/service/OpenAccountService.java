package org.learn.AccountOpeningDemo.service;

import org.learn.AccountOpeningDemo.Exception.CustomerNotFoundException;
import org.learn.AccountOpeningDemo.model.OpenCurrentAccountRequest;
import org.learn.AccountOpeningDemo.model.OpenCurrentAccountResponse;

/**
 * Service interface for OpenAccountService
 */
public interface OpenAccountService {
    /**
     *
     * @param openCurrentAccountRequest
     * @return OpenCurrentAccountResponse
     * @throws CustomerNotFoundException
     */
    OpenCurrentAccountResponse openCurrentAccount(OpenCurrentAccountRequest openCurrentAccountRequest) throws CustomerNotFoundException;
}
