# AccountOpeningDemo

This project covers below given points related to opening of a current account for an existing customer.

Two below given existing customers are created after projects starts up.
1. Customer ID: 1, Customer Name: rahul, Surname: bidada
2. Customer ID: 2, Customer Name: george, Surname: clooney

This application exposes two below given endpoints.
1. Endpoint which opens a new current account. It accepts customer ID of an existing customer and Initial Credit amount to be credited in newly opened account.
2. Endpoint which shows information about the customer like accounts of customer and transactions made on those accounts.

A new account can be opened by sending a POST request on below given endpoint and using sample body given below.
endpoint: localhost:8080/account/currentbankaccount
body for Post operation: 
{
    "customerId":"1",
    "initialCredit":"10"
}


Information of the customer can be retrieved by sending a GET request on below given endpoint
endpoint: localhost:8080/customer/customerinformation/{customerID}

