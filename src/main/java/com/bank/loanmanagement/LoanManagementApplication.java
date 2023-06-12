package com.bank.loanmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.bank.loanmanagement"})
public class LoanManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanManagementApplication.class, args);
	}
}
