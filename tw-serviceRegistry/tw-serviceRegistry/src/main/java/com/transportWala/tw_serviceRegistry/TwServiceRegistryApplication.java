package com.transportWala.tw_serviceRegistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TwServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwServiceRegistryApplication.class, args);
	}

}
