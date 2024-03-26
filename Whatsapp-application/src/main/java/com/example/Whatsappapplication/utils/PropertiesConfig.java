package com.example.Whatsappapplication.utils;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("file:src/main/resources/application.properties")
public class PropertiesConfig {

	
	@Value("${Recieve.baseURl}")
	private String RecieveBaseURL;
	
	@Value("${Reminder.baseURl}")
	private String ReminderBaseURL;

	public String getRecieveBaseURL() {
		return RecieveBaseURL;
	}

	public void setRecieveBaseURL(String recieveBaseURL) {
		RecieveBaseURL = recieveBaseURL;
	}

	public String getReminderBaseURL() {
		return ReminderBaseURL;
	}

	public void setReminderBaseURL(String reminderBaseURL) {
		ReminderBaseURL = reminderBaseURL;
	}
	
	
}
