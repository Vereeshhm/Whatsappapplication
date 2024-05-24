package com.example.Whatsappapplication.Service;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Whatsappapplication.Entity.insurancedto;

@Service
public interface Insuranceservice {

	ResponseEntity<insurancedto> getinsuranceByMobileNumber(String mobile_no) throws IOException;

}
