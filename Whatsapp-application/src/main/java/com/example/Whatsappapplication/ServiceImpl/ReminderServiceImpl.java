package com.example.Whatsappapplication.ServiceImpl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import com.example.Whatsappapplication.Entity.Reminderdto;


import com.example.Whatsappapplication.Service.WhatsappService;
import com.example.Whatsappapplication.Service.WhatsappService1;

import com.example.Whatsappapplication.ServiceResponse.WhatsaAppResponse;
import com.example.Whatsappapplication.utils.PropertiesConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@Service
@Transactional
@Validated
public class ReminderServiceImpl implements WhatsappService1 {

	
	
	
	
	@Autowired
	PropertiesConfig propertiesConfig;
	
	
	private final JdbcTemplate jdbcTemplate;
	
	public ReminderServiceImpl(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate=jdbcTemplate;
	}

	@Override
	public ResponseEntity<Reminderdto> getRecordByMobileNumber(String mobile_no) throws IOException {
		
		
		String query="SELECT customer_name,saswat_loan_number,emi_date,mobile_no FROM loandetailss WHERE mobile_no = ?";
		
		
		List<Reminderdto> results= jdbcTemplate.query(query, ps -> ps.setString(1, mobile_no), new ReminderdtoRowMapper());
		
		if(results.isEmpty())
		{
			return ResponseEntity.notFound().build();
		}
		else {
			
			Reminderdto dto=results.get(0);
			
			
			String customer_name=dto.getCustomer_name();
			String saswat_loan_number=dto.getSaswat_loan_number();
			String emi_date=dto.getEmi_date();
			
			String baseUrl=propertiesConfig.getReminderBaseURL();
			String fullURL=String.format("%s?customer_name=%s&saswat_loan_number=%s&emi_date=%s&mobile_no=%s",
					baseUrl, customer_name, saswat_loan_number, emi_date, mobile_no);
			
			
			ObjectMapper objectMapper=new ObjectMapper();
			
			
			String payloadJson="{\"payload\":{\"name\":\"emi_reminder\",\"components\":[{\"type\":\"body\",\"parameters\":[{\"type\":\"text\",\"text\":\""+dto.getCustomer_name()+"\"},"
					+ "{\"type\":\"text\",\"text\":\""+dto.getSaswat_loan_number()+"\"},{\"type\":\"text\",\"text\":\""+dto.getEmi_date()+"\"}]}],\"language\":{\"code\":\"en_US\",\"policy\":\"deterministic\"},"
					+ "\"namespace\":\"ef357b92_0da8_4375_b8fb_6a09202df2bc\"},\"phoneNumber\":\""+dto.getMobile_no()+"\"}";
			
			
			URL url=new URL(fullURL);
		    HttpURLConnection connection=(HttpURLConnection)url.openConnection();
		    connection.setRequestMethod("GET");
		    connection.setRequestProperty("Authorization", " Basic 3b6bdb88-58b8-4e80-af2f-5f7c055d6cf0-HkDULO5");
		    connection.setRequestProperty("Content-Type", "application/json");
		    connection.setDoOutput(true);
		    
		    
		    DataOutputStream outputStream=new DataOutputStream(connection.getOutputStream());
		    
		    outputStream.flush();
		    outputStream.write(payloadJson.getBytes());
		    
		    BufferedReader in =new BufferedReader(new InputStreamReader(connection.getInputStream()));
		    StringBuilder response =new StringBuilder();
		    String inputLine;
		    while((inputLine = in.readLine()) != null) {
		    	response.append(inputLine);
		    }
		    in.close();
		    
		    JsonNode jsonResponse = objectMapper.readTree(response.toString());

			// Here you can use the payloadJson string to send the message to WhatsApp
			// Assuming you have a method to send the payload to WhatsApp

			// Return the ResponseEntity with the Saswatdto object

			return ResponseEntity.ok(dto);
		    
			
		}
		
		
	}
	
	private static class ReminderdtoRowMapper implements RowMapper<Reminderdto>
	{
		
		
		@Override
		public Reminderdto mapRow(ResultSet rs,int rowNum) throws SQLException{
			
			Reminderdto dto=new Reminderdto();
		
			dto.setCustomer_name(rs.getString("customer_name"));
			dto.setEmi_date(rs.getString("emi_date"));
			dto.setSaswat_loan_number(rs.getString("saswat_loan_number"));
			dto.setMobile_no(rs.getString("mobile_no"));
			
			return dto;
			
			
		}
	}
	
	
	
	
	
	
}