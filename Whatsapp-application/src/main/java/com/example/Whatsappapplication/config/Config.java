package com.example.Whatsappapplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


import com.example.Whatsappapplication.ServiceImpl.WhatsappServiceImpl;


@Configuration
public class Config {

	
	
	@Bean
	public WhatsappServiceImpl whatsappServiceImpl() {
		return new WhatsappServiceImpl();
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
