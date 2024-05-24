package com.example.Whatsappapplication.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.Whatsappapplication.Entity.insurancedto;
import com.example.Whatsappapplication.Entity.prospectdto;
import com.example.Whatsappapplication.Repository.Prospectrepo;
import com.example.Whatsappapplication.Repository.insurancerepo;
import com.example.Whatsappapplication.Service.Insuranceservice;
import com.example.Whatsappapplication.Service.Prospectservice;
import com.example.Whatsappapplication.ServiceImpl.Insurance2serviceimpl;
import com.example.Whatsappapplication.ServiceImpl.Insuranceserviceimpl;
import com.example.Whatsappapplication.ServiceImpl.Prospectserviceimpl;

@SpringBootTest
public class InsuranceserviceimplTest {

	
	
	@Mock
	private Insuranceservice insuranceservice;

	@Mock
	private insurancerepo insurancerepo;

	@InjectMocks
	private Insuranceserviceimpl insuranceserviceimpl;

	@Mock
	private JdbcTemplate jdbcTemplate;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetinsuranceByMobileNumber() throws IOException {

		String mobileNumber = "9844963948";

		insurancedto dto = new insurancedto();

		dto.setCustomer_id("Vishal Kamate");
		dto.setInsurance_exists("Yes");
		dto.setLanguage_selected("null");
		dto.setSaswat_insurance_number_1("null");
		dto.setSaswat_insurance_number_2("null");
		dto.setSaswat_insurance_number_3("null");
		dto.setSaswat_insurance_number_4("null");
		dto.setSaswat_insurance_number_5("null");
		dto.setMobile_no(mobileNumber);
		dto.setTotal("1");

		when(this.insurancerepo.findByMobile_no(anyString())).thenReturn(Collections.singletonList(dto));
	    ResponseEntity<insurancedto> expectedResponse = new ResponseEntity<>(dto, HttpStatus.OK);
		ResponseEntity<insurancedto> response = insuranceserviceimpl.getinsuranceByMobileNumber(mobileNumber);

		assertEquals(200, response.getStatusCodeValue());
		System.out.println("Responsee  " + response.getStatusCodeValue() + "    " + response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

}
