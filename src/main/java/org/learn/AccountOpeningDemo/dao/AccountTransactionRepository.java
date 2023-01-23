package org.learn.AccountOpeningDemo.dao;

import org.learn.AccountOpeningDemo.entity.AccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for entity AccountTransaction
 */
public interface AccountTransactionRepository extends JpaRepository<AccountTransaction, Long> {
}
