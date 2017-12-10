package com.polsl.jakubwidlak.LoyaltyManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import sun.applet.Main;

@SpringBootApplication
public class LoyaltyManagementApplication {

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(LoyaltyManagementApplication.class);
		builder.headless(false).run(args);
		//new SpringApplicationBuilder(Main.class).headless(false).run(args);
		//SpringApplication.run(LoyaltyManagementApplication.class, args);
	}
}
