package com.example.Whatsappapplication.ServiceImpl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import com.example.Whatsappapplication.Entity.Saswatdto;
import com.example.Whatsappapplication.Repository.WhatsAppRepository;

import com.example.Whatsappapplication.Service.WhatsappService;

import com.example.Whatsappapplication.ServiceResponse.WhatsaAppResponse;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@Service
@Transactional
@Validated
public class WhatsappServiceImpl implements WhatsappService {

	@Autowired
	WhatsAppRepository whatsAppRepository;
//	
//	
	@Autowired
	RestTemplate restTemplate;
	// private WhatsaAppResponse whatsappResponse;

	@Override
	public WhatsaAppResponse getEmiNotification(Saswatdto dto) {
		WhatsaAppResponse whatsappResponse = new WhatsaAppResponse();

		// Status status=new Status();

		try {

			String basURl = "https://api.wab.ai/whatsapp-api/v1.0/customer/95106/bot/fb07e17049ff432d/template";

			String name = dto.getName();
			long amount = dto.getAmount();
			Long loan = dto.getLoan();
			
			String formattedDate=dto.getFormattedDate();

			String number = dto.getNumber();

			// construct payload json
			ObjectMapper objectMapper = new ObjectMapper();

			// Define the payload data
			String payloadJson = "{\"payload\":{\"name\":\"emi_received\",\"components\":[{\"type\":\"body\",\"parameters\":[{\"type\":\"text\",\"text\":\""
					+ name + "\"},{\"type\":\"text\",\"text\":\"" + amount + "\"},{\"type\":\"text\",\"text\":\"" + loan
					+ "\"},{\"type\":\"text\",\"text\":\"" + formattedDate
					+ "\"}]}],\"language\":{\"code\":\"en_US\",\"policy\":\"deterministic\"},\"namespace\":\"ef357b92_0da8_4375_b8fb_6a09202df2bc\"},\"phoneNumber\":\""
					+ number + "\"}";

			String fullURL = String.format("%s?name=%s&amount=%d&loan=%d&formattedDate=%s&number=%s", basURl, name,
					amount, loan, formattedDate, number);
			// setup the connection

			URL url = new URL(fullURL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
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

			// Construct and return the response object

			// whatsappResponse.setResponseObject(new
			// ResponseObject(jsonResponse.get("responseObject").get("message_id").asText()));
//	       whatsappResponse.setStatus( new Status(jsonResponse.get("status").get("code").asInt()));
//	       whatsappResponse.setStatus( new Status(jsonResponse.get("status").get("desc").asText()));
//	       String messageId=jsonResponse.get("responseObject").get("message_id").asText();
//	       whatsappResponse.setResponseObject(new ResponseObject(messageId));
			int statusCode = jsonResponse.get("status").get("code").asInt();
			String statusDesc = jsonResponse.get("status").get("desc").asText();

			if (statusCode == 1000) {

				if (loan != null && String.valueOf(loan).length() == 14) {
					if (number != null && number.length() == 10 && number.matches("\\d+")) {

						Saswatdto entity = new Saswatdto();

						entity.setName(dto.getName());
						entity.setAmount(dto.getAmount());

						entity.setFormattedDate(dto.getFormattedDate());
						
						entity.setLoan(dto.getLoan());
						entity.setNumber(dto.getNumber());

						whatsAppRepository.save(entity);

						whatsappResponse.setDesc("SUCCESS");
						whatsappResponse.setStatus("sent successfully");

					} else {
						whatsappResponse.setDesc("Failed");

						whatsappResponse.setStatus("Failed to send - phone number must be 10 digits");

					}
				} else {
					whatsappResponse.setDesc("Failed");
					whatsappResponse.setStatus("Failed to send -  loan number must be exactly 14 digits");

				}
			} else {
				whatsappResponse.setStatus("Failed to send");
			}

			whatsappResponse.setCode(statusCode);
		 // whatsappResponse.setDesc(statusDesc);

			outputStream.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return whatsappResponse;

	}

}
