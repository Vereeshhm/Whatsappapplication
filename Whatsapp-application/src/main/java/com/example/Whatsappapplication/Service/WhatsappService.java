package com.example.Whatsappapplication.Service;

import java.net.ProtocolException;

import org.springframework.stereotype.Service;


import com.example.Whatsappapplication.Entity.Saswatdto;
import com.example.Whatsappapplication.ServiceResponse.WhatsaAppResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;






public interface WhatsappService {

	 public WhatsaAppResponse getEmiNotification(Saswatdto dto) throws JsonProcessingException;
	//WhatsaAppResponse getEmiNotification(emiapi ami) throws ProtocolException;

	

	
	

	
	
}
