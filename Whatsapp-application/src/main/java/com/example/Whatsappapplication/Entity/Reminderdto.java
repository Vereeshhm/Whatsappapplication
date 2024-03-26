package com.example.Whatsappapplication.Entity;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.validation.constraints.Pattern;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="loandetailss")
public class Reminderdto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private String mobile_no;
	
	
	private String saswat_loan_number;
	
	
	private String customer_name;

    
	private String emi_date;


	
	
	public String getMobile_no() {
		return mobile_no;
	}


	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}


	public String getSaswat_loan_number() {
		return saswat_loan_number;
	}


	public void setSaswat_loan_number(String saswat_loan_number) {
		this.saswat_loan_number = saswat_loan_number;
	}






	public String getCustomer_name() {
		return customer_name;
	}


	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}


	public String getEmi_date() {
		return emi_date;
	}


	public void setEmi_date(String emi_date) {
		this.emi_date = emi_date;
	}



	
	
	
}
