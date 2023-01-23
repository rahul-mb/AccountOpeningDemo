package org.learn.AccountOpeningDemo.dao;

import org.learn.AccountOpeningDemo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for the entity Customer
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
