package dev.cleaningservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.SpringVersion;

@SpringBootApplication
public class CleaningserviceApplication {

	public static void main(String[] args) {System.out.println("Spring version: " + SpringVersion.getVersion());
		SpringApplication.run(CleaningserviceApplication.class, args);
	}

}
