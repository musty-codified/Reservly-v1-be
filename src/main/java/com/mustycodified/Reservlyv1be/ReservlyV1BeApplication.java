package com.mustycodified.Reservlyv1be;

import com.mustycodified.Reservlyv1be.security.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReservlyV1BeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservlyV1BeApplication.class, args);
	}
	@Bean(name = "AppProperties")
	public AppProperties getAppProperties(){
		return new AppProperties();
	}
}
