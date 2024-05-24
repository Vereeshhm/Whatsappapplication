package com.example.Whatsappapplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;




@Configuration
public class Config {

//	@Bean
//	public WhatsappServiceImpl whatsappServiceImpl() {
//		return new WhatsappServiceImpl();
//	}
//	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

//	@Bean
//	public ReminderServiceImpl reminderServiceImpl() {
//		return new ReminderServiceImpl();
//	}
}
