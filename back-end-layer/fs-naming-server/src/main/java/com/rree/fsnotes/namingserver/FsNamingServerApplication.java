package com.rree.fsnotes.namingserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class FsNamingServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FsNamingServerApplication.class, args);
	}

}
