package org.learn.AccountOpeningDemo.service;

import org.learn.AccountOpeningDemo.Exception.CustomerNotFoundException;
import org.learn.AccountOpeningDemo.model.CustomerInformationResponse;

public interface RetrieveCustomerInfoService {

    CustomerInformationResponse retrieveCustomerInfo(Long CustomerId) throws CustomerNotFoundException;
}
