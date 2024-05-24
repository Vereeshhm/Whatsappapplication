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

import com.example.Whatsappapplication.Entity.insurance2dto;

import com.example.Whatsappapplication.Repository.insurancerepo1;

import com.example.Whatsappapplication.Service.insuranceservice1;
import com.example.Whatsappapplication.ServiceImpl.Insurance2serviceimpl;

@SpringBootTest
public class Insuranceservice1ImplTest {

	

	@Mock
	private insuranceservice1 insuranceservice1;

	@Mock
	private insurancerepo1 insurancerepo1;

	@InjectMocks
	private Insurance2serviceimpl insurance2serviceimpl;

	@Mock
	private JdbcTemplate jdbcTemplate;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetinsurance1ByMobileNumber() throws IOException {

		String mobileNumber = "9110471783";

		insurance2dto dto = new insurance2dto();

		dto.setCustomer_id("Karthik");
		dto.setInsurance_from("Yes");
		dto.setInsurance_status_active("Active");
		dto.setInsurance_type("Cattle Insurance");
		dto.setInsurance_validity_date("03-Feb-26");
		dto.setInsured_amount("130000");
		dto.setInsured_date("3-Nov-2023");
		dto.setLoan_from("null");
		dto.setMobile_no(mobileNumber);
		dto.setNo_of_cattle("2");
		dto.setInsurance_from("null");
		dto.setPolicy_no_of_insurance("Test5485");
		dto.setSaswat_loan_number("null");
		
		
		when(this.insurancerepo1.findByMobile_no(anyString())).thenReturn(Collections.singletonList(dto));
	    ResponseEntity<insurance2dto> expectedResponse = new ResponseEntity<>(dto, HttpStatus.OK);
		ResponseEntity<insurance2dto> response = insurance2serviceimpl.getinsurance1ByMobileNumber(mobileNumber);

		assertEquals(200, response.getStatusCodeValue());
		System.out.println("Responsee  " + response.getStatusCodeValue() + "    " + response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}
