package com.example.Whatsappapplication.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RestController;

import com.example.Whatsappapplication.Entity.Reminderdto;
import com.example.Whatsappapplication.Entity.Saswatdto;
import com.example.Whatsappapplication.Entity.insurance2dto;
import com.example.Whatsappapplication.Entity.insurancedto;
import com.example.Whatsappapplication.Entity.prospectdto;
import com.example.Whatsappapplication.Service.Insuranceservice;
import com.example.Whatsappapplication.Service.Prospectservice;
//import com.example.Whatsappapplication.Repository.WhatsAppRepository;
//import com.example.Whatsappapplication.Repository.WhatsappRepo1;
import com.example.Whatsappapplication.Service.WhatsappService;
import com.example.Whatsappapplication.Service.WhatsappService1;
import com.example.Whatsappapplication.Service.insuranceservice1;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
//import java.util.Base64;

@RestController

public class Whatsappcontroller {

	@Autowired
	WhatsappService whatsappService;

	@Autowired
	WhatsappService1 whatsappService1;

	@Autowired
	Prospectservice prospectservice;

	@Autowired
	Insuranceservice insuranceservice;

	@Autowired
	insuranceservice1 insuranceservice1;

	@GetMapping("emi/mobile_no/{mobile_no}")
	public ResponseEntity<ResponseEntity<Saswatdto>> getRecordsByMobileNumber(@PathVariable String mobile_no)
			throws MalformedURLException, ProtocolException, IOException {

		return ResponseEntity.ok(whatsappService.getRecordsByMobileNumber(mobile_no));
	}

	@GetMapping("emi/reminder/mobile_no/{mobile_no}")
	public ResponseEntity<ResponseEntity<Reminderdto>> getRecordByMobileNumber(@PathVariable String mobile_no)
			throws MalformedURLException, IOException {

		return ResponseEntity.ok(whatsappService1.getRecordByMobileNumber(mobile_no));
	}

	@GetMapping("emi/prospect/mobileno/{mobileno}")
	public ResponseEntity<ResponseEntity<prospectdto>> getByMobileNumber(@PathVariable String mobileno)
			throws IOException {
		return ResponseEntity.ok(prospectservice.getByMobileNumber(mobileno));
	}

	@GetMapping("emi/insurance/mobile_no/{mobile_no}")
	public ResponseEntity<ResponseEntity<insurancedto>> getinsuranceByMobileNumber(@PathVariable String mobile_no)
			throws IOException {
		return ResponseEntity.ok(insuranceservice.getinsuranceByMobileNumber(mobile_no));
	}

	@GetMapping("emi/insurance1/mobile_no/{mobile_no}")
	public ResponseEntity<ResponseEntity<insurance2dto>> getinsurance1ByMobileNumber(@PathVariable String mobile_no)
			throws IOException {
		return ResponseEntity.ok(insuranceservice1.getinsurance1ByMobileNumber(mobile_no));
	}
	

}
