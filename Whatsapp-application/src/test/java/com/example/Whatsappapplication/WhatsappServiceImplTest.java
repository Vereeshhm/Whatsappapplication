package com.example.Whatsappapplication;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.Whatsappapplication.Controller.Whatsappcontroller;
import com.example.Whatsappapplication.Entity.Saswatdto;
import com.example.Whatsappapplication.Repository.WhatsAppRepository;
import com.example.Whatsappapplication.Service.WhatsappService;
import com.example.Whatsappapplication.ServiceResponse.WhatsaAppResponse;
import com.example.Whatsappapplication.utils.PropertiesConfig;
import com.example.Whatsappapplication.ServiceImpl.WhatsappServiceImpl;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class WhatsappServiceImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private PropertiesConfig propertiesConfig;

    @InjectMocks
    private WhatsappServiceImpl whatsappServiceImpl;

    @Mock
    private WhatsappService whatsappService;
//
//    @Mock
//    private Whatsappcontroller whatsappcontroller;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(whatsappServiceImpl).build();
        whatsappService = new WhatsappServiceImpl(jdbcTemplate);
    }

    @org.junit.jupiter.api.Test
    public void testGetRecordsByMobileNumber() throws SQLException, MalformedURLException, ProtocolException, IOException {
        String mobile_no = "9880418761";

        // Create a dummy Saswatdto with expected data
        Saswatdto expectedDto = new Saswatdto();
        expectedDto.setCustomer_name("TestKarthik");
        expectedDto.setEmi_amt("7185");
        expectedDto.setSaswat_loan_number("Test48923655861");
        expectedDto.setEmi_date("2-5-2024");
        expectedDto.setMobile_no(mobile_no);

        // Mock the query method of JdbcTemplate to return the expected data
        when(jdbcTemplate.query(anyString(), any(RowMapper.class), anyString())).thenAnswer(invocation -> {
            RowMapper<Saswatdto> rowMapper = invocation.getArgument(1);
            return Collections.singletonList(rowMapper.mapRow(null, 0));
        });

        // Mock the propertiesConfig to return the expected base URL
      //  when(propertiesConfig.getRecieveBaseURL()).thenReturn("https://api.wab.ai/whatsapp-api/v1.0/customer/95106/bot/fb07e17049ff432d/template");

        // Call the method under test
        ResponseEntity<Saswatdto> response = whatsappService.getRecordsByMobileNumber(mobile_no);

        // Assert that the response is not null and has a status code of 200 OK
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Assert that the response body matches the expected Saswatdto
        Saswatdto responseDto = response.getBody();
        assertNotNull(responseDto);
        assertEquals(expectedDto.getCustomer_name(), responseDto.getCustomer_name());
        assertEquals(expectedDto.getEmi_amt(), responseDto.getEmi_amt());
        assertEquals(expectedDto.getSaswat_loan_number(), responseDto.getSaswat_loan_number());
        assertEquals(expectedDto.getEmi_date(), responseDto.getEmi_date());
        assertEquals(expectedDto.getMobile_no(), responseDto.getMobile_no());
    }
}