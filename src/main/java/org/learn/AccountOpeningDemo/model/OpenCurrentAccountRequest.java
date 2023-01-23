package org.learn.AccountOpeningDemo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

/**
 * Model class for Request to open the account
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OpenCurrentAccountRequest {
    /** * Unique customer ID which need to be greater than zero     */
    @Positive
    private long customerId;

    /**  * Amount of the transaction for initial credit after opeing the account      */
    @DecimalMin(value = "0.0")
    private BigDecimal initialCredit;
}
