package com.example.Whatsappapplication.Service;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.http.ResponseEntity;

import com.example.Whatsappapplication.Entity.Reminderdto;


public interface WhatsappService1 {

	ResponseEntity<Reminderdto> getRecordByMobileNumber(String mobile_no) throws MalformedURLException, IOException;

	
}
