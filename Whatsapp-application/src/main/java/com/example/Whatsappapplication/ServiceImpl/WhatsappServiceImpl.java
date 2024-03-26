package com.example.Whatsappapplication.ServiceImpl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;


import com.example.Whatsappapplication.Entity.Saswatdto;
import com.example.Whatsappapplication.Repository.WhatsAppRepository;

import com.example.Whatsappapplication.Service.WhatsappService;

import com.example.Whatsappapplication.ServiceResponse.WhatsaAppResponse;
import com.example.Whatsappapplication.utils.PropertiesConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;

@Service
@Transactional
@Validated
public class WhatsappServiceImpl implements WhatsappService {

	@Autowired
	PropertiesConfig propertiesConfig;


	private final JdbcTemplate jdbcTemplate;

	public WhatsappServiceImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public ResponseEntity<Saswatdto> getRecordsByMobileNumber(String mobile_no) throws IOException {

		String query = "SELECT customer_name, emi_amt, saswat_loan_number, emi_date, mobile_no FROM loandetailss WHERE mobile_no = ?";

		List<Saswatdto> results = jdbcTemplate.query(query, ps -> ps.setString(1, mobile_no), new SaswatdtoRowMapper());

		if (results.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			 //return ResponseEntity.ok(results.get(0));
			Saswatdto dto = results.get(0);

			String customer_name = dto.getCustomer_name();
			String emi_amt = dto.getEmi_amt();
			String saswat_loan_number = dto.getSaswat_loan_number();

			String emi_date = dto.getEmi_date();
			String baseUrl = propertiesConfig.getRecieveBaseURL();
			String fullURL = String.format("%s?customer_name=%s&emi_amt=%s&saswat_loan_number=%s&emi_date=%s&number=%s",
					baseUrl, customer_name, emi_amt, saswat_loan_number, emi_date, mobile_no);

			ObjectMapper objectMapper = new ObjectMapper();
			// Construct the payload JSON string
			String payloadJson = "{\"payload\":{\"name\":\"emi_received\",\"components\":[{\"type\":\"body\",\"parameters\":[{\"type\":\"text\",\"text\":\""
					+ dto.getCustomer_name() + "\"},{\"type\":\"text\",\"text\":\"" + dto.getEmi_amt()
					+ "\"},{\"type\":\"text\",\"text\":\"" + dto.getSaswat_loan_number()
					+ "\"},{\"type\":\"text\",\"text\":\"" + dto.getEmi_date()
					+ "\"}]}],\"language\":{\"code\":\"en_US\",\"policy\":\"deterministic\"},\"namespace\":\"ef357b92_0da8_4375_b8fb_6a09202df2bc\"},\"phoneNumber\":\""
					+ dto.getMobile_no() + "\"}";
			URL url = new URL(fullURL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Authorization", "Basic 3b6bdb88-58b8-4e80-af2f-5f7c055d6cf0-HkDULO5");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setDoOutput(true);

			DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());

			outputStream.flush();
			outputStream.write(payloadJson.getBytes());

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder response = new StringBuilder();
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
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

	private static class SaswatdtoRowMapper implements RowMapper<Saswatdto> {
		@Override
		public Saswatdto mapRow(ResultSet rs, int rowNum) throws SQLException {
			Saswatdto dto = new Saswatdto();
			// Map ResultSet columns to DTO fields
			dto.setEmi_amt(rs.getString("emi_amt"));
			dto.setSaswat_loan_number(rs.getString("saswat_loan_number"));
			dto.setEmi_date(rs.getString("emi_date"));
			dto.setMobile_no(rs.getString("mobile_no"));
		    dto.setCustomer_name(rs.getString("customer_name"));
		    

	   
		    
		   
			return dto;
		}
		  

	}


}





