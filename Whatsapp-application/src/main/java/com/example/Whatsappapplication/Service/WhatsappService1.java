package com.example.Whatsappapplication.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;

import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import com.example.Whatsappapplication.Entity.Reminderdto;


@Service
public interface WhatsappService1 {

	

	ResponseEntity<Reminderdto> getRecordByMobileNumber(String mobile_no)
			throws MalformedURLException, ProtocolException, IOException;


}
