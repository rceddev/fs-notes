package com.rree.fsnotes.fsgateway.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FsGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(FsGatewayApplication.class, args);
	}

}
