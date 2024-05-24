package com.example.Whatsappapplication.Service;

import java.io.IOException;

import java.net.MalformedURLException;
import java.net.ProtocolException;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//import com.example.Whatsappapplication.Entity.Reminderdto;
import com.example.Whatsappapplication.Entity.Saswatdto;






@Service
public interface WhatsappService {

	ResponseEntity<Saswatdto> getRecordsByMobileNumber(String mobile_no)
			throws MalformedURLException, ProtocolException, IOException;


	
}