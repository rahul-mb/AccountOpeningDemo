package org.learn.AccountOpeningDemo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Model class for the response of the open current account operation
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OpenCurrentAccountResponse {

    /** * Unique customer ID    */
    private long customerId;

    /** * Unique account ID generated as a part of operation open current account     */
    private long accountId;

    /** * Unique transaction ID generated as a part of operation open current account    */
    private long transactionId;
}
