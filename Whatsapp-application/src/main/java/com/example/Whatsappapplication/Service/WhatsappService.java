package com.example.Whatsappapplication.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

//import com.example.Whatsappapplication.Entity.Reminderdto;
import com.example.Whatsappapplication.Entity.Saswatdto;

import com.example.Whatsappapplication.ServiceResponse.WhatsaAppResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;






public interface WhatsappService {

	ResponseEntity<Saswatdto> getRecordsByMobileNumber(String mobile_no) throws MalformedURLException, ProtocolException, IOException;

	


	

	
	

	
	
}
