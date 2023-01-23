package org.learn.AccountOpeningDemo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Entity class for transactions of the account
 */
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AccountTransaction {

    /** * primary key and unique ID of a transaction */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionId;

    /** type of transaction i.e. credit or debit    */
    private String transactionType;

    /** amount of the transaction     */
    private BigDecimal amount;

    /** Many transactions can belong to one account     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", nullable = false)
    private CustomerAccount customerAccount;
}
