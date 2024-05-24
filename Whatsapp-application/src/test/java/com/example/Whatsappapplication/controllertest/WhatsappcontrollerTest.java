
package com.example.Whatsappapplication.controllertest;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.Whatsappapplication.Controller.Whatsappcontroller;
import com.example.Whatsappapplication.Entity.Reminderdto;
import com.example.Whatsappapplication.Entity.Saswatdto;
import com.example.Whatsappapplication.Entity.insurance2dto;
import com.example.Whatsappapplication.Entity.insurancedto;
import com.example.Whatsappapplication.Entity.prospectdto;
import com.example.Whatsappapplication.Service.Insuranceservice;
import com.example.Whatsappapplication.Service.Prospectservice;
import com.example.Whatsappapplication.Service.WhatsappService;
import com.example.Whatsappapplication.Service.WhatsappService1;
import com.example.Whatsappapplication.Service.insuranceservice1;
import com.example.Whatsappapplication.dto.Reminderdata;
import com.example.Whatsappapplication.dto.Saswatdata;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


@SpringBootTest
public class WhatsappcontrollerTest {

	@Mock
	private WhatsappService whatsappService;

	@Mock
	private WhatsappService1 whatsappService1;
	
     @Mock
   private insuranceservice1 insuranceservice1;
     
     @Mock
     private Prospectservice prospectservice;
			
	@Mock
	private Insuranceservice insuranceservice;

	@InjectMocks
	private Whatsappcontroller whatsappcontroller;

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);

		mockMvc = MockMvcBuilders.standaloneSetup(whatsappcontroller).build();
	}

	@Test
	public void testGetRecordsByMobileNumber() throws Exception {

		Saswatdto dto = new Saswatdto(); // create a mock Saswatdto object

		when(whatsappService.getRecordsByMobileNumber(anyString())).thenReturn(ResponseEntity.ok(dto));

		ResultActions resultActions = mockMvc.perform(get("/emi/mobile_no/9880418761"));

		resultActions.andExpect(status().isOk());

		System.out.println("Responsecontent" + resultActions.andReturn().getResponse().getContentAsString());

	}

	@Test
	public void testGetRecordByMobileNumber() throws Exception {

		Reminderdto dto1 = new Reminderdto();

		when(whatsappService1.getRecordByMobileNumber(anyString())).thenReturn(ResponseEntity.ok(dto1));

		ResultActions resultActions = mockMvc.perform(get("/emi/reminder/mobile_no/9880418761"));

		resultActions.andExpect(status().isOk());

		System.out.println("Responsecontent" + resultActions.andReturn().getResponse().getContentAsString());
	}

	
	
	@Test
	public void testGetInsuranceByMobileNumber() throws Exception {

		insurancedto entity = new insurancedto();

		when(insuranceservice.getinsuranceByMobileNumber(anyString())).thenReturn(ResponseEntity.ok(entity));

		ResultActions resultActions = mockMvc.perform(get("/emi/insurance/mobile_no/9844963948"));

		resultActions.andExpect(status().isOk());

		System.out.println("Responsecontent" + resultActions.andReturn().getResponse().getContentAsString());
	}
	
	@Test
	public void testGetInsurance1ByMobileNumber() throws Exception {

		insurance2dto entity = new insurance2dto();

		when(insuranceservice1.getinsurance1ByMobileNumber(anyString())).thenReturn(ResponseEntity.ok(entity));

		ResultActions resultActions = mockMvc.perform(get("/emi/insurance1/mobile_no/9110471783"));

		resultActions.andExpect(status().isOk());

		System.out.println("Responsecontent" + resultActions.andReturn().getResponse().getContentAsString());
	}
	
	
	@Test
	public void testGetByMobileNumber() throws Exception {

		prospectdto entity = new prospectdto();

		when(prospectservice.getByMobileNumber(anyString())).thenReturn(ResponseEntity.ok(entity));

		ResultActions resultActions = mockMvc.perform(get("/emi/prospect/mobileno/9788737833"));

		resultActions.andExpect(status().isOk());

		System.out.println("Responsecontent" + resultActions.andReturn().getResponse().getContentAsString());
	}
	
	
	
	
	
	
	
	
	
}
