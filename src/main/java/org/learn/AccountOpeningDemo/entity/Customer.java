package org.learn.AccountOpeningDemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity class for Customer table
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Customer{

    /**
     * Unique identifier for each customer of the bank
     *  As for this exercise creation of customer is not in scope so generation is not considered.
     */
    @Id
    private long customerId;

    /** * Customer name   */
    private String customerName;

    /** * Surname of the customer    */
    private String Surname;

    /**     * One customer can have multiple current accounts     */
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<CustomerAccount> customerAccounts = new ArrayList<>();
}
