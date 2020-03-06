package com.revature.stock.stockservice.resource;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {
	@LoadBalanced    //client side load balance
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();   // this allow us to have only once instance of RestTemplate running all time. 
	}
		
}
