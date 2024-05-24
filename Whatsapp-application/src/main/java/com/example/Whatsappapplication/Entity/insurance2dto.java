package com.example.Whatsappapplication.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="insurancetb2")
public class insurance2dto {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	
	
	private String mobile_no;
	
	private String customer_id;
	
	
	private String policy_no_of_insurance;
	
	private String saswat_loan_number;
	
	private String loan_from;
	
	private String insurance_from;
	
	private String insured_amount;
	
	private String no_of_cattle;
	
	
	private String insured_date;
	
	private String insurance_validity_date;
	
	private String insurance_type;
	
	private String insurance_status_active;

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public String getPolicy_no_of_insurance() {
		return policy_no_of_insurance;
	}

	public void setPolicy_no_of_insurance(String policy_no_of_insurance) {
		this.policy_no_of_insurance = policy_no_of_insurance;
	}

	public String getSaswat_loan_number() {
		return saswat_loan_number;
	}

	public void setSaswat_loan_number(String saswat_loan_number) {
		this.saswat_loan_number = saswat_loan_number;
	}

	public String getLoan_from() {
		return loan_from;
	}

	public void setLoan_from(String loan_from) {
		this.loan_from = loan_from;
	}

	public String getInsurance_from() {
		return insurance_from;
	}

	public void setInsurance_from(String insurance_from) {
		this.insurance_from = insurance_from;
	}

	public String getInsured_amount() {
		return insured_amount;
	}

	public void setInsured_amount(String insured_amount) {
		this.insured_amount = insured_amount;
	}

	public String getNo_of_cattle() {
		return no_of_cattle;
	}

	public void setNo_of_cattle(String no_of_cattle) {
		this.no_of_cattle = no_of_cattle;
	}

	public String getInsured_date() {
		return insured_date;
	}

	public void setInsured_date(String insured_date) {
		this.insured_date = insured_date;
	}

	public String getInsurance_validity_date() {
		return insurance_validity_date;
	}

	public void setInsurance_validity_date(String insurance_validity_date) {
		this.insurance_validity_date = insurance_validity_date;
	}

	public String getInsurance_type() {
		return insurance_type;
	}

	public void setInsurance_type(String insurance_type) {
		this.insurance_type = insurance_type;
	}

	public String getInsurance_status_active() {
		return insurance_status_active;
	}

	public void setInsurance_status_active(String insurance_status_active) {
		this.insurance_status_active = insurance_status_active;
	}

	@Override
	public String toString() {
		return "insurance2dto [mobile_no=" + mobile_no + ", customer_id=" + customer_id + ", policy_no_of_insurance="
				+ policy_no_of_insurance + ", saswat_loan_number=" + saswat_loan_number + ", loan_from=" + loan_from
				+ ", insurance_from=" + insurance_from + ", insured_amount=" + insured_amount + ", no_of_cattle="
				+ no_of_cattle + ", insured_date=" + insured_date + ", insurance_validity_date="
				+ insurance_validity_date + ", insurance_type=" + insurance_type + ", insurance_status_active="
				+ insurance_status_active + "]";
	}
	
	
	
	
	
	
}
