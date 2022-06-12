package com.sap.claimvalidation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class ClaimValidationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClaimValidationApplication.class, args);
	}

}
