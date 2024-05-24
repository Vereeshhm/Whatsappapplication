package com.example.Whatsappapplication.Service;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Whatsappapplication.Entity.insurance2dto;

@Service
public interface insuranceservice1 {

	ResponseEntity<insurance2dto> getinsurance1ByMobileNumber(String mobile_no) throws IOException;

}
