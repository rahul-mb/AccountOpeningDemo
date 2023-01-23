package org.learn.AccountOpeningDemo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Model class for Account of the customer
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    /**  * Unique idenfier for an account     */
    private long accountId;

    /** * List of the transactions of an account     */
    private List<Transaction> transactionList;
}
