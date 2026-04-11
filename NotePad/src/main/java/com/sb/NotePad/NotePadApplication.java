package com.sb.NotePad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;

@SpringBootApplication
public class NotePadApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotePadApplication.class, args);
	}

}
