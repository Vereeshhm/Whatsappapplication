package com.example.Whatsappapplication.repositorytest;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.Whatsappapplication.Entity.Reminderdto;
import com.example.Whatsappapplication.Entity.insurancedto;
import com.example.Whatsappapplication.Repository.Reminderrepository;
import com.example.Whatsappapplication.Repository.insurancerepo;


@SpringBootTest
public class insurancerepotest {

	@Autowired
	private insurancerepo insurancerepo;

	@Test
	public void testFindByMobileNo() {

		insurancedto dto1 = new insurancedto();

		dto1.setMobile_no("9844963948");

		List<insurancedto> result = insurancerepo.findByMobile_no("9844963948");

		assertNotNull(result);

		// Print the response body
		System.out.println("Response body: " + result);
		assertEquals(1, result.size());
		assertEquals("9844963948", result.get(0).getMobile_no());

	}
}
