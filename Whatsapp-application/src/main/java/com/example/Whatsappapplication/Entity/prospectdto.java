package com.example.Whatsappapplication.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="prospect_new")
public class prospectdto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private String mobileno;
	
	private String statusofapp;
	
	private String language_selected;
	
	
	private String pin_code;
	
	private String state;
	
	private String district;
	
	private String dateddmmyy;
	
	private String time;
	
	private String status;

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getStatusofapp() {
		return statusofapp;
	}

	public void setStatusofapp(String statusofapp) {
		this.statusofapp = statusofapp;
	}

	public String getLanguage_selected() {
		return language_selected;
	}

	public void setLanguage_selected(String language_selected) {
		this.language_selected = language_selected;
	}

	public String getPin_code() {
		return pin_code;
	}

	public void setPin_code(String pin_code) {
		this.pin_code = pin_code;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	

	

	public String getDateddmmyy() {
		return dateddmmyy;
	}

	public void setDateddmmyy(String dateddmmyy) {
		this.dateddmmyy = dateddmmyy;
	}



	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "prospectdto [mobileno=" + mobileno + ", statusofapp=" + statusofapp + ", language_selected="
				+ language_selected + ", pin_code=" + pin_code + ", state=" + state + ", district=" + district
				+ ", dateddmmyy=" + dateddmmyy + ", time=" + time + ", status=" + status + "]";
	}
	
	
	
}
