package org.learn.AccountOpeningDemo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.learn.AccountOpeningDemo.entity.CustomerAccount;
import java.math.BigDecimal;


/**
 * Model class for the transaction of the account
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    /** * Unique transaction id of a trasaction     */
    private long transactionId;

    /** * Tranaction type i.e. credit or debut     */
    private String transactionType;

    /** * Amount of the transaction     */
    private BigDecimal amount;
}
