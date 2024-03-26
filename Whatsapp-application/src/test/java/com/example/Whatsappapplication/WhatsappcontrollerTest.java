package com.example.Whatsappapplication;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.Whatsappapplication.Controller.Whatsappcontroller;
import com.example.Whatsappapplication.Entity.Reminderdto;
import com.example.Whatsappapplication.Entity.Saswatdto;
import com.example.Whatsappapplication.Service.WhatsappService;
import com.example.Whatsappapplication.Service.WhatsappService1;
import com.example.Whatsappapplication.ServiceImpl.WhatsappServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest

public class WhatsappcontrollerTest {

    @Mock
    private WhatsappService whatsappService;

    @Mock
    private WhatsappService1 whatsappService1;


    @InjectMocks
    private Whatsappcontroller whatsappcontroller;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
    	
        mockMvc = MockMvcBuilders.standaloneSetup(whatsappcontroller).build();
    }

   @org.junit.jupiter.api.Test
    public void testGetRecordsByMobileNumber() throws Exception {
	 
        Saswatdto dto = new Saswatdto(); // create a mock Saswatdto object
    
     
        when(whatsappService.getRecordsByMobileNumber(anyString())).thenReturn(ResponseEntity.ok(dto)); 
        
        ResultActions resultActions = mockMvc.perform(get("/emi/mobile_no/9880418761"));

        resultActions.andExpect(status().isOk());
  
       //System.out.println("Responsecontent"+resultActions.andReturn().getResponse().getContentAsString());
       
   }
   
    
   
   @Test
   public void testGetRecordByMobileNumber() throws Exception{
	   
	   Reminderdto dto= new Reminderdto();
	   when(whatsappService1.getRecordByMobileNumber(anyString())).thenReturn(ResponseEntity.ok(dto));
	   
	   ResultActions resultActions=mockMvc.perform(get("/emi/reminder/mobile_no/9880418761"));
	   
	   resultActions.andExpect(status().isOk());
   }
   
   
   
   
   
}
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   

