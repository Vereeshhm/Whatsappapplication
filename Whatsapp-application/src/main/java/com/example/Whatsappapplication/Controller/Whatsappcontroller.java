package com.example.Whatsappapplication.Controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Whatsappapplication.Entity.Reminderdto;

import com.example.Whatsappapplication.Entity.Saswatdto;
import com.example.Whatsappapplication.Service.WhatsappService;
import com.example.Whatsappapplication.Service.WhatsappService1;
import com.example.Whatsappapplication.ServiceImpl.WhatsappServiceImpl;

import com.example.Whatsappapplication.ServiceResponse.WhatsaAppResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/emi")
public class Whatsappcontroller {

	@Autowired
	WhatsappService whatsappService;

	@Autowired
	WhatsappService1 whatsappService1;


	@GetMapping("/mobile_no/{mobile_no}")
	public ResponseEntity<ResponseEntity<Saswatdto>> getRecordsByMobileNumber(@PathVariable String mobile_no)
			throws MalformedURLException, ProtocolException, IOException {
		return ResponseEntity.ok(whatsappService.getRecordsByMobileNumber(mobile_no));
	}

	@GetMapping("/reminder/mobile_no/{mobile_no}")
	public ResponseEntity<ResponseEntity<Reminderdto>> getRecordByMobileNumber(@PathVariable String mobile_no) throws MalformedURLException, IOException {
		return ResponseEntity.ok(whatsappService1.getRecordByMobileNumber(mobile_no));
	}

}
