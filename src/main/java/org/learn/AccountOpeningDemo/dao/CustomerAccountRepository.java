package org.learn.AccountOpeningDemo.dao;

import org.learn.AccountOpeningDemo.entity.CustomerAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for the entity CustomerAccount
 */
public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, Long> {
}
