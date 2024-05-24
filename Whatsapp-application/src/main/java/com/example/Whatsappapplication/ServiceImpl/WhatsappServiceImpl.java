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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import com.example.Whatsappapplication.Entity.Saswatdto;
import com.example.Whatsappapplication.Repository.Sasrepository;
//import com.example.Whatsappapplication.Repository.Sasrepository;
import com.example.Whatsappapplication.Repository.WhatsAppRepository;

import com.example.Whatsappapplication.Service.WhatsappService;

import com.example.Whatsappapplication.ServiceResponse.WhatsaAppResponse;
import com.example.Whatsappapplication.dto.Saswatdata;
import com.example.Whatsappapplication.utils.PropertiesConfig;

import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
@Validated
public class WhatsappServiceImpl implements WhatsappService {

	@Autowired
	PropertiesConfig propertiesConfig;

	@Autowired
	WhatsAppRepository whatsAppRepository;

	@Autowired
	Sasrepository sasrepository;

	public void Sasrepository(Sasrepository sasrepository) {
		this.sasrepository = sasrepository;
	}

	private JdbcTemplate jdbcTemplate;

	public WhatsappServiceImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public ResponseEntity<Saswatdto> getRecordsByMobileNumber(String mobile_no) throws IOException {

		// First, try to fetch records from the Spring Data JPA repository
		List<Saswatdto> resultsJPA = this.sasrepository.findByMobile_no(mobile_no);

		// If no records found in JPA repository, fall back to JdbcTemplate
		if (resultsJPA.isEmpty()) {
			String query = "SELECT customer_name, emi_amt, saswat_loan_number, emi_date, mobile_no FROM loandetailss WHERE mobile_no = ?";
			
			
			List<Saswatdto> resultsJdbcTemplate = jdbcTemplate.query(query, ps -> ps.setString(1, mobile_no),
					new SaswatdtoRowMapper());

			if (resultsJdbcTemplate.isEmpty()) {
				return ResponseEntity.notFound().build();
			} else {

				return processResults(resultsJdbcTemplate.get(0));

			}
		} else {
			return processResults(resultsJPA.get(0));

		}
	}

	private ResponseEntity<Saswatdto> processResults(Saswatdto dto) throws IOException {
		// Assuming only one record is fetched

		// Save the fetched record to another entity

		Saswatdata entity = new Saswatdata();
		entity.setCustomer_name(dto.getCustomer_name());
		entity.setEmi_amt(dto.getEmi_amt());
		entity.setEmi_date(dto.getEmi_date());
		entity.setMobile_no(dto.getMobile_no());
		entity.setSaswat_loan_number(dto.getSaswat_loan_number());
		whatsAppRepository.save(entity);

		// Construct URL and payload for API request
		String baseUrl = propertiesConfig.getRecieveBaseURL();
		
		String fullURL = String.format("%s?customer_name=%s&emi_amt=%s&saswat_loan_number=%s&emi_date=%s&number=%s",
				baseUrl, dto.getCustomer_name(), dto.getEmi_amt(), dto.getSaswat_loan_number(), dto.getEmi_date(),
				dto.getMobile_no());

		ObjectMapper objectMapper = new ObjectMapper();
		String payloadJson = "{\"payload\":{\"name\":\"emi_received\",\"components\":[{\"type\":\"body\",\"parameters\":[{\"type\":\"text\",\"text\":\""
				+ dto.getCustomer_name() + "\"},{\"type\":\"text\",\"text\":\"" + dto.getEmi_amt()
				+ "\"},{\"type\":\"text\",\"text\":\"" + dto.getSaswat_loan_number()
				+ "\"},{\"type\":\"text\",\"text\":\"" + dto.getEmi_date()
				+ "\"}]}],\"language\":{\"code\":\"en_US\",\"policy\":\"deterministic\"},\"namespace\":\"ef357b92_0da8_4375_b8fb_6a09202df2bc\"},\"phoneNumber\":\""
				+ dto.getMobile_no() + "\"}";

		
		
		
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
		return ResponseEntity.ok(dto);
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
