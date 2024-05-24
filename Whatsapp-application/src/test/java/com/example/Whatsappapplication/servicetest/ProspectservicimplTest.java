package com.example.Whatsappapplication.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
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

import com.example.Whatsappapplication.Entity.Reminderdto;
import com.example.Whatsappapplication.Entity.prospectdto;
import com.example.Whatsappapplication.Repository.Prospectrepo;
import com.example.Whatsappapplication.Service.Prospectservice;
import com.example.Whatsappapplication.ServiceImpl.Prospectserviceimpl;

@SpringBootTest
public class ProspectservicimplTest {

	@Mock
	private Prospectrepo prospectrepo;

	@Mock
	private Prospectservice prospectservice;

	@InjectMocks
	private Prospectserviceimpl prospectserviceimpl;

	@Mock
	private JdbcTemplate jdbcTemplate;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetByMobileNumber() throws IOException {

		String mobileNumber = "9788737833";

		prospectdto dto = new prospectdto();

		dto.setDateddmmyy("2024-04-04");
		dto.setDistrict("Pune");
		dto.setLanguage_selected("English");
		dto.setPin_code("5600782");
		dto.setState("Maharashtra");
		dto.setStatus("Not Interested");
		dto.setStatusofapp("NEW");
		dto.setTime("18-07-53");
		dto.setMobileno(mobileNumber);

		when(this.prospectrepo.findByMobileno(anyString())).thenReturn(Collections.singletonList(dto));
	    ResponseEntity<prospectdto> expectedResponse = new ResponseEntity<>(dto, HttpStatus.OK);
		ResponseEntity<prospectdto> response = prospectserviceimpl.getByMobileNumber(mobileNumber);

		assertEquals(200, response.getStatusCodeValue());
		System.out.println("Responsee  " + response.getStatusCodeValue() + "    " + response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

}
