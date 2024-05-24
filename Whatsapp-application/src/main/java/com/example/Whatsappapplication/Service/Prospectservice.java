package com.example.Whatsappapplication.Service;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.example.Whatsappapplication.Entity.prospectdto;

@Service
public interface Prospectservice {

	ResponseEntity<prospectdto> getByMobileNumber(String mobileno) throws IOException;

	

}
