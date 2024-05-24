package com.example.Whatsappapplication.Entity;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@javax.persistence.Entity
@javax.persistence.Table(name="loandetailss")
public class Saswatdto {

	@Id
	@javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	
	

	@Column(name="mobile_no")
	private String mobile_no;
	
	 @Column(name="emi_amt")
	private String emi_amt;
	
	 @Max(value = 14)
	 @javax.validation.constraints.Pattern(regexp = "\\d{14}", message = "Loan number must be exactly 14 digits")
	 @Column(name="saswat_loan_number")
	private String saswat_loan_number;
	
      
	private String customer_name;
	
	
	
	private String emi_date;
	



	public String getEmi_amt() {
		return emi_amt;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public void setEmi_amt(String emi_amt) {
		this.emi_amt = emi_amt;
	}

	public String getEmi_date() {
		return emi_date;
	}

	public void setEmi_date(String emi_date) {
		this.emi_date = emi_date;
	}

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

	@Override
	public String toString() {
		return "Saswatdto [mobile_no=" + mobile_no + ", emi_amt=" + emi_amt + ", saswat_loan_number="
				+ saswat_loan_number + ", customer_name=" + customer_name + ", emi_date=" + emi_date + "]";
	}


	
   


	
	

	


	
	
	
	
}
