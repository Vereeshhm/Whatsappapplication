package com.example.Whatsappapplication.repositorytest;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.Whatsappapplication.Entity.Reminderdto;
import com.example.Whatsappapplication.Repository.Reminderrepository;

@SpringBootTest
public class Reminderrepotest {

	@Autowired
	private Reminderrepository reminderrepository;

	@Test
	public void testFindByMobileNo() {

		Reminderdto dto1 = new Reminderdto();

		dto1.setMobile_no("9880418761");

		List<Reminderdto> result = reminderrepository.findByMobile_no("9880418761");

		assertNotNull(result);

		// Print the response body
		System.out.println("Response body: " + result);
		assertEquals(1, result.size());
		assertEquals("9880418761", result.get(0).getMobile_no());

	}
}
