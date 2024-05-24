package com.example.Whatsappapplication.repositorytest;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.Whatsappapplication.Entity.Reminderdto;
import com.example.Whatsappapplication.Entity.prospectdto;
import com.example.Whatsappapplication.Repository.Prospectrepo;
import com.example.Whatsappapplication.Repository.Reminderrepository;


@SpringBootTest
public class Prospectrepotest {

	
	@Autowired
	private Prospectrepo prospectrepo;

	@Test
	public void testFindByMobileNo() {

		prospectdto dto1 = new prospectdto();

		dto1.setMobileno("9788737833");

		List<prospectdto> result = prospectrepo.findByMobileno("9788737833");

		assertNotNull(result);

		// Print the response body
		System.out.println("Response body: " + result);
		assertEquals(69, result.size());
		assertEquals("9788737833", result.get(0).getMobileno());

	}
}
