package com.smirnov.technicaltest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

@Controller
@SpringBootApplication
public class TechnicalTestApplication {
	public static void main(String[] args) {
		SpringApplication.run(TechnicalTestApplication.class, args);
	}
}
