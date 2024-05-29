package com.rree.fsnotes.persistance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FsNotesPersistanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FsNotesPersistanceApplication.class, args);
	}

}
