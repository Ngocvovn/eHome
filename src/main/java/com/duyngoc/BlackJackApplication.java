package com.duyngoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@ComponentScan(basePackages = {"com.duyngoc"})
public class BlackJackApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlackJackApplication.class, args);
	}
}
