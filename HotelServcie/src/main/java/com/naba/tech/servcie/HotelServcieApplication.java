package com.naba.tech.servcie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class HotelServcieApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelServcieApplication.class, args);
	}

}
