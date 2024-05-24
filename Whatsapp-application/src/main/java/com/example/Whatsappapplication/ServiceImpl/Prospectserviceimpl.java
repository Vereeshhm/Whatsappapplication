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

import com.example.Whatsappapplication.Entity.prospectdto;
import com.example.Whatsappapplication.Repository.Prospectrepo;

import com.example.Whatsappapplication.Service.Prospectservice;

@Service
public class Prospectserviceimpl implements Prospectservice {

	
	@Autowired
	Prospectrepo prospectrepo;
  	
	
	
	public void Sasrepository(Prospectrepo prospectrepo) {
		this.prospectrepo = prospectrepo;
	}

	private JdbcTemplate jdbcTemplate;

	public Prospectserviceimpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	

	@Override
	public ResponseEntity<prospectdto> getByMobileNumber(String mobileno) throws IOException {
	
		
		List<prospectdto> resultsJPA = this.prospectrepo.findByMobileno(mobileno);

		// If no records found in JPA repository, fall back to JdbcTemplate
		if (resultsJPA.isEmpty()) {
			String query = "SELECT mobileno, statusofapp, language_selected, pin_code, state,district,dateddmmyy,time,status FROM prospect_new WHERE mobileno = ?";
			
			
			List<prospectdto> resultsJdbcTemplate = jdbcTemplate.query(query, ps -> ps.setString(1, mobileno),
					new prospectdtoRowMapper());

			if (resultsJdbcTemplate.isEmpty()) {
				return ResponseEntity.notFound().build();
			} else {

				return processResults(resultsJdbcTemplate.get(0));

			}
		} else {
			return processResults(resultsJPA.get(0));

		}
		
	}
	private ResponseEntity<prospectdto> processResults(prospectdto dto) throws IOException {
		
		return ResponseEntity.ok(dto);
	}
	
	private static class prospectdtoRowMapper implements RowMapper<prospectdto> {
		@Override
		public prospectdto mapRow(ResultSet rs, int rowNum) throws SQLException {
			prospectdto dto = new prospectdto();
			// Map ResultSet columns to DTO fields
			dto.setMobileno(rs.getString("mobileno"));
			dto.setDateddmmyy(rs.getString("dateddmmyy"));
			dto.setDistrict(rs.getString("district"));
			dto.setLanguage_selected(rs.getString("language_selected"));
			dto.setPin_code(rs.getString("pin_code"));	
			dto.setState(rs.getString("state"));
			dto.setStatus(rs.getString("status"));
			dto.setStatusofapp(rs.getString("statusofapp"));
			dto.setTime(rs.getString("time"));
			return dto;
		}
	}

	
	
	

}
