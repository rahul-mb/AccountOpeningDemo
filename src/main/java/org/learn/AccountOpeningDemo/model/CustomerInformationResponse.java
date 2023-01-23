package org.learn.AccountOpeningDemo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Model class for response which contains information
 * about the customer, its accounts and their transactions
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerInformationResponse {

    /**  * Unique identifier for a customer     */
    private long customerId;

    /**  *  Customer Name     */
    private String customerName;

    /** * Surname of the customer      */
    private String Surname;

    /** List of the accounts of the customer     */
    private List<Account> accountList;
}
