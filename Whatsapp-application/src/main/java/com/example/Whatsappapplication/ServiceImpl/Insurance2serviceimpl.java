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

import com.example.Whatsappapplication.Entity.insurance2dto;

import com.example.Whatsappapplication.Repository.insurancerepo1;
import com.example.Whatsappapplication.Service.insuranceservice1;

@Service
public class Insurance2serviceimpl implements insuranceservice1 {

	@Autowired
	insurancerepo1 insurancerepo1;

	public void insurancerepo(insurancerepo1 insurancerepo1) {
		this.insurancerepo1 = insurancerepo1;
	}

	private JdbcTemplate jdbcTemplate;

	public Insurance2serviceimpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public ResponseEntity<insurance2dto> getinsurance1ByMobileNumber(String mobile_no) throws IOException {

		List<insurance2dto> resultsJPA = this.insurancerepo1.findByMobile_no(mobile_no);

		// If no records found in JPA repository, fall back to JdbcTemplate
		if (resultsJPA.isEmpty()) {
			String query = "SELECT mobile_no, customer_id, policy_no_of_insurance,insurance_from , insurance_status_active,insurance_type,insured_amount,insurance_validity_date,saswat_loan_number,no_of_cattle,insured_date,loan_from FROM insurancetb2 WHERE mobile_no = ?";

			List<insurance2dto> resultsJdbcTemplate = jdbcTemplate.query(query, ps -> ps.setString(1, mobile_no),
					new insurance2dtoRowMapper());

			if (resultsJdbcTemplate.isEmpty()) {
				return ResponseEntity.notFound().build();
			} else {

				return processResults(resultsJdbcTemplate.get(0));

			}
		} else {
			return processResults(resultsJPA.get(0));

		}
	}

	private ResponseEntity<insurance2dto> processResults(insurance2dto dto) throws IOException {

		return ResponseEntity.ok(dto);
	}

	private static class insurance2dtoRowMapper implements RowMapper<insurance2dto> {
		@Override
		public insurance2dto mapRow(ResultSet rs, int rowNum) throws SQLException {
			insurance2dto dto = new insurance2dto();
			// Map ResultSet columns to DTO fields
			dto.setCustomer_id(rs.getString("customer_id"));
			dto.setInsurance_from(rs.getString("insurance_from"));
			dto.setMobile_no(rs.getString("mobile_no"));
			dto.setInsurance_status_active(rs.getString("insurance_status_active"));
			dto.setInsurance_type(rs.getString("insurance_type"));
			dto.setInsurance_validity_date(rs.getString("insurance_validity_date"));
			dto.setInsured_amount(rs.getString("insured_amount"));
			dto.setLoan_from(rs.getString("loan_from"));
			dto.setSaswat_loan_number(rs.getString("saswat_loan_number"));
			dto.setPolicy_no_of_insurance(rs.getString("policy_no_of_insurance"));
			dto.setNo_of_cattle(rs.getString("no_of_cattle"));
			dto.setInsured_date(rs.getString("insured_date"));
			return dto;
		}
	}

}
