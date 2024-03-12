package com.example.Whatsappapplication.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Whatsappapplication.Entity.Saswatdto;
import com.example.Whatsappapplication.Service.WhatsappService;
import com.example.Whatsappapplication.ServiceImpl.WhatsappServiceImpl;

import com.example.Whatsappapplication.ServiceResponse.WhatsaAppResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/emi")
public class Whatsappcontroller {

	@Autowired
	WhatsappService whatsappService;

	@PostMapping(value = "/notification", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WhatsaAppResponse> sendEmiNotification(@RequestBody Saswatdto dto) {
		try {
			WhatsaAppResponse response = whatsappService.getEmiNotification(dto);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
