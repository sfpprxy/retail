package com.epam.retail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class RetailApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetailApplication.class, args);
	}

}
