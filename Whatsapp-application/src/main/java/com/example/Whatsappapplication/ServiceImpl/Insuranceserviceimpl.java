package com.example.Whatsappapplication.ServiceImpl;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.example.Whatsappapplication.Entity.insurancedto;
import com.example.Whatsappapplication.Repository.insurancerepo;
import com.example.Whatsappapplication.Service.Insuranceservice;

@Service
public class Insuranceserviceimpl implements Insuranceservice {

	@Autowired
	insurancerepo insurancerepo;

	public void insurancerepo(insurancerepo insurancerepo) {
		this.insurancerepo = insurancerepo;
	}

	private JdbcTemplate jdbcTemplate;

	public Insuranceserviceimpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public ResponseEntity<insurancedto> getinsuranceByMobileNumber(String mobile_no) throws IOException {

		List<insurancedto> resultsJPA = this.insurancerepo.findByMobile_no(mobile_no);

		// If no records found in JPA repository, fall back to JdbcTemplate
		if (resultsJPA.isEmpty()) {
			String query = "SELECT mobile_no, customer_id, language_selected,insurance_exists , saswat_insurance_number_1,saswat_insurance_number_2,saswat_insurance_number_3,saswat_insurance_number_4,saswat_insurance_number_5,total FROM insurancetb1 WHERE mobile_no = ?";

			List<insurancedto> resultsJdbcTemplate = jdbcTemplate.query(query, ps -> ps.setString(1, mobile_no),
					new insurancedtoRowMapper());

			if (resultsJdbcTemplate.isEmpty()) {
				return ResponseEntity.notFound().build();
			} else {

				return processResults(resultsJdbcTemplate.get(0));

			}
		} else {
			return processResults(resultsJPA.get(0));

		}
	}

	private ResponseEntity<insurancedto> processResults(insurancedto dto) throws IOException {

		return ResponseEntity.ok(dto);
	}

	private static class insurancedtoRowMapper implements RowMapper<insurancedto> {
		@Override
		public insurancedto mapRow(ResultSet rs, int rowNum) throws SQLException {
			insurancedto dto = new insurancedto();
			// Map ResultSet columns to DTO fields
			dto.setCustomer_id(rs.getString("customer_id"));
			dto.setInsurance_exists(rs.getString("insurance_exists"));
			dto.setMobile_no(rs.getString("mobile_no"));
			dto.setLanguage_selected(rs.getString("language_selected"));
			dto.setSaswat_insurance_number_1(rs.getString("saswat_insurance_number_1"));
			dto.setSaswat_insurance_number_2(rs.getString("saswat_insurance_number_2"));
			dto.setSaswat_insurance_number_3(rs.getString("saswat_insurance_number_3"));
			dto.setSaswat_insurance_number_4(rs.getString("saswat_insurance_number_4"));
			dto.setSaswat_insurance_number_5(rs.getString("saswat_insurance_number_5"));
			dto.setTotal(rs.getString("total"));
			return dto;
		}
	}

}
