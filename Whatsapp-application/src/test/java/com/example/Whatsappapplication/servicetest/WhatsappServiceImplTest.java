package com.example.Whatsappapplication.servicetest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;



import com.example.Whatsappapplication.Entity.Saswatdto;
import com.example.Whatsappapplication.Repository.Sasrepository;
import com.example.Whatsappapplication.Repository.WhatsAppRepository;
import com.example.Whatsappapplication.Service.WhatsappService;
import com.example.Whatsappapplication.ServiceImpl.WhatsappServiceImpl;
import com.example.Whatsappapplication.utils.PropertiesConfig;

@SpringBootTest
public class WhatsappServiceImplTest {

	@Mock
	private Sasrepository sasrepository;

	@Mock
	private WhatsAppRepository whatsAppRepository;

	@Mock
	private WhatsappService whatsappServicee;

	@Mock
	private PropertiesConfig propertiesConfig;

	@Mock
	private JdbcTemplate jdbcTemplate;

	@InjectMocks
	private WhatsappServiceImpl whatsappService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetRecordsByMobileNumber() throws IOException, MalformedURLException {
		// Arrange

		String mobileNumber = "9880418761";
		Saswatdto dto = new Saswatdto();
		dto.setCustomer_name("karthika");
		dto.setEmi_amt("87888");
		dto.setEmi_date("04-03-2024");
		dto.setMobile_no(mobileNumber);
		dto.setSaswat_loan_number("878884543");

		// Mock behavior of sasrepository
		when(sasrepository.findByMobile_no(anyString())).thenReturn(Collections.singletonList(dto));

		when(propertiesConfig.getRecieveBaseURL())
				.thenReturn("https://api.wab.ai/whatsapp-api/v1.0/customer/95106/bot/fb07e17049ff432d/template");
		// Test the method
		ResponseEntity<Saswatdto> response = whatsappService.getRecordsByMobileNumber(mobileNumber);

		// Verify the response
		assertEquals(200, response.getStatusCodeValue());
		System.out.println("Responsee  " + response.getStatusCodeValue() + "    " + response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());

	}
}

//ANother method for testing 

//String mobileNo = "9880418761";
//
//// Mock behavior of sasrepository.findByMobile_no
//List<Saswatdto> resultsJPA = Collections.singletonList(new Saswatdto());
//when(sasrepository.findByMobile_no(mobileNo)).thenReturn(resultsJPA);
//
//// Mock behavior of propertiesConfig.getRecieveBaseURL()
//when(propertiesConfig.getRecieveBaseURL()).thenReturn("https://api.wab.ai/whatsapp-api/v1.0/customer/95106/bot/fb07e17049ff432d/template");
//
//// Act
//ResponseEntity<Saswatdto> responseEntity = whatsappService.getRecordsByMobileNumber(mobileNo);
//
//// Assert
//assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//assertEquals(resultsJPA.get(0), responseEntity.getBody());