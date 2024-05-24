package com.example.Whatsappapplication.repositorytest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.Whatsappapplication.Entity.Saswatdto;
import com.example.Whatsappapplication.Repository.Sasrepository;

@SpringBootTest
public class SasrepositoryTest {

	@Autowired

	private Sasrepository sasrepository;

	@Test
	public void testFindByMobileNo() {

		Saswatdto saswatdto = new Saswatdto();
		saswatdto.setMobile_no("9880418761");

		List<Saswatdto> result = sasrepository.findByMobile_no("9880418761");

		assertEquals(1, result.size());
		assertNotNull(result);

		System.out.println("Response body: " + result);
		assertEquals("9880418761", result.get(0).getMobile_no());
	}

}
