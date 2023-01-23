package org.learn.AccountOpeningDemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Entity class for the table account of the customer
 */
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAccount {

    /** * Unique identifier for an account     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accountId;

    /** *Many account can belong to one customer     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    /** * One account can have multiple transactions     */
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customerAccount", cascade = CascadeType.ALL)
    private List<AccountTransaction> accountTransactions;
}
