package org.learn.AccountOpeningDemo;

import org.learn.AccountOpeningDemo.dao.CustomerRepository;
import org.learn.AccountOpeningDemo.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AccountOpeningDemoApplication {

	@Autowired
	private CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(AccountOpeningDemoApplication.class, args);
	}

	@Bean
	public void loadData(){

		customerRepository.save(new Customer(1l, "rahul", "bidada",null));
		customerRepository.save(new Customer(2l, "George", "clooney", null));
	}

}


