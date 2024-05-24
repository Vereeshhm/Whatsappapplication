package com.example.Whatsappapplication.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.Collections;
import java.util.List;
import com.example.Whatsappapplication.Entity.Reminderdto;
import com.example.Whatsappapplication.Repository.Reminderrepository;
import com.example.Whatsappapplication.Repository.WhatsappRepo1;
import com.example.Whatsappapplication.Service.WhatsappService1;
import com.example.Whatsappapplication.ServiceImpl.ReminderServiceImpl;
import com.example.Whatsappapplication.utils.PropertiesConfig;



@SpringBootTest
public class ReminderServiceImplTest {

	
	
	  @Mock
	  private Reminderrepository reminderrepository;
	  
	  @InjectMocks
	  private ReminderServiceImpl reminderService;
	  
	  @Mock
	  private PropertiesConfig propertiesConfig;
	  
	  @Mock
	  private JdbcTemplate jdbcTemplate;
	  
	  @Mock
	  private WhatsappRepo1 whatsappRepo1;
	  
	  
	  @Mock
	  private WhatsappService1 whatsappService1;
	  
	  
	  
	  
	  
	  
	  @BeforeEach
	  public void setUp() {
		  MockitoAnnotations.initMocks(this);
	  }
	  
	  
	  @Test
	  public void testGetRecordByMobileNumber() throws MalformedURLException, ProtocolException, IOException {
		  
		  String mobileNumber="9880418761";
		  
		  Reminderdto dto=new Reminderdto();
		  
		  dto.setCustomer_name("karthika");
		  dto.setEmi_date("04-03-2024");
		  dto.setSaswat_loan_number("878884543");
		  dto.setMobile_no(mobileNumber);
		  
		  
		  when(reminderrepository.findByMobile_no(anyString())).thenReturn(Collections.singletonList(dto));
		  
		  when(propertiesConfig.getReminderBaseURL()).thenReturn("https://api.wab.ai/whatsapp-api/v1.0/customer/95106/bot/fb07e17049ff432d/template");

		  ResponseEntity<Reminderdto> response=reminderService.getRecordByMobileNumber(mobileNumber);
		  
		  
		  assertEquals(200,response.getStatusCodeValue());
		  System.out.println("Responsee  " + response.getStatusCodeValue() + "    " + response.getBody());
		  assertEquals(HttpStatus.OK, response.getStatusCode());
	  }
	  
	  
	  
}
