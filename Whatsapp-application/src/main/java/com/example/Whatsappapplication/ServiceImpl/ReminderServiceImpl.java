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

import com.example.Whatsappapplication.Repository.Reminderrepository;


import com.example.Whatsappapplication.Repository.WhatsappRepo1;

import com.example.Whatsappapplication.Service.WhatsappService1;

import com.example.Whatsappapplication.dto.Reminderdata;

import com.example.Whatsappapplication.utils.PropertiesConfig;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service

@Validated
public class ReminderServiceImpl implements WhatsappService1 {

	
	
	@Autowired
	PropertiesConfig propertiesConfig;

	@Autowired
	WhatsappRepo1 whatsappRepo1;

	@Autowired
	Reminderrepository reminderrepository;

	public void Reminderrepository(Reminderrepository reminderrepository) {
		this.reminderrepository = reminderrepository;
	}

	private JdbcTemplate jdbcTemplate;

	public ReminderServiceImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public ResponseEntity<Reminderdto> getRecordByMobileNumber(String mobile_no) throws IOException {

		// First, try to fetch records from the Spring Data JPA repository
		List<Reminderdto> resultsJPA = this.reminderrepository.findByMobile_no(mobile_no);

		// If no records found in JPA repository, fall back to JdbcTemplate
		if (resultsJPA.isEmpty()) {
			String query = "SELECT customer_name, saswat_loan_number, emi_date, mobile_no FROM loandetailss WHERE mobile_no = ?";
			
			List<Reminderdto> resultsJdbcTemplate = jdbcTemplate.query(query, ps -> ps.setString(1, mobile_no),
					new ReminderdtoRowMapper());

			if (resultsJdbcTemplate.isEmpty()) {
				return ResponseEntity.notFound().build();
			} else {

				return processResults(resultsJdbcTemplate.get(0));

			}
		} else {
			return processResults(resultsJPA.get(0));

		}
	}

	private ResponseEntity<Reminderdto> processResults(Reminderdto dto1) throws IOException {
		// Assuming only one record is fetched

		// Save the fetched record to another entity

		Reminderdata entity = new Reminderdata();
		entity.setCustomer_name(dto1.getCustomer_name());
	
		entity.setEmi_date(dto1.getEmi_date());
		entity.setMobile_no(dto1.getMobile_no());
		entity.setSaswat_loan_number(dto1.getSaswat_loan_number());
		whatsappRepo1.save(entity);

		// Construct URL and payload for API request
		String baseUrl = propertiesConfig.getReminderBaseURL();
		
		String fullURL = String.format("%s?customer_name=%s&saswat_loan_number=%s&emi_date=%s&number=%s",
				baseUrl, dto1.getCustomer_name(),  dto1.getSaswat_loan_number(), dto1.getEmi_date(),
				dto1.getMobile_no());

		ObjectMapper objectMapper = new ObjectMapper();

		String payloadJson="{\"payload\":{\"name\":\"emi_reminder\",\"components\":[{\"type\":\"body\",\"parameters\":[{\"type\":\"text\",\"text\":\""+dto1.getCustomer_name()+"\"},"
				+ "{\"type\":\"text\",\"text\":\""+dto1.getSaswat_loan_number()+"\"},{\"type\":\"text\",\"text\":\""+dto1.getEmi_date()+"\"}]}],\"language\":{\"code\":\"en_US\",\"policy\":\"deterministic\"},"
				+ "\"namespace\":\"ef357b92_0da8_4375_b8fb_6a09202df2bc\"},\"phoneNumber\":\""+dto1.getMobile_no()+"\"}";


		// Send HTTP GET request
		URL url = new URL(fullURL);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Authorization", "Basic 3b6bdb88-58b8-4e80-af2f-5f7c055d6cf0-HkDULO5");
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setDoOutput(true);

		// Write payload to output stream
		DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
		outputStream.write(payloadJson.getBytes());
		outputStream.flush();

		// Read and process the response
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuilder response = new StringBuilder();
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// Process response JSON
		JsonNode jsonResponse = objectMapper.readTree(response.toString());

		// Return the fetched record
		return ResponseEntity.ok(dto1);
	}

	private static class ReminderdtoRowMapper implements RowMapper<Reminderdto> {
		@Override
		public Reminderdto mapRow(ResultSet rs, int rowNum) throws SQLException {
			Reminderdto dto1 = new Reminderdto();
			// Map ResultSet columns to DTO fields
		
			dto1.setSaswat_loan_number(rs.getString("saswat_loan_number"));
			dto1.setEmi_date(rs.getString("emi_date"));
			dto1.setMobile_no(rs.getString("mobile_no"));
			dto1.setCustomer_name(rs.getString("customer_name"));
			return dto1;
		}
	}
	
	
	
}