package com.example.Whatsappapplication.Entity;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.validation.constraints.Max;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name="whatsapp")
public class Saswatdto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name="id")
	private long id;
	
	@Column(name="name")
	private String name;
	
	 @javax.validation.constraints.Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 digits")
	 @Column(name="number")
	private String number;
	
	 @Column(name="amount")
	private long amount;
	
	 @Max(value = 14)
	 @javax.validation.constraints.Pattern(regexp = "\\d{14}", message = "Loan number must be exactly 14 digits")
	 @Column(name="loan")
	private Long loan;
	

	@Column(name="formattedDate")
	private String formattedDate;

      
      
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public long getLoan() {
		return loan;
	}

	public void setLoan(long loan) {
		this.loan = loan;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public String getFormattedDate() {
		return formattedDate;
	}

	public void setFormattedDate(String formattedDate) {
		this.formattedDate = formattedDate;
	}

	

	public void setLoan(Long loan) {
		this.loan = loan;
	}


	
	

	public Saswatdto() {
		super();
		this.id = id;
		this.name = name;
		this.number = number;
		this.amount = amount;
		this.loan = loan;
	    this.formattedDate=generateFormattedDate();
	}

	private String generateFormattedDate() {
		long timestamp=System.currentTimeMillis();
		Date date=new Date(timestamp);
		SimpleDateFormat sdf=new SimpleDateFormat();
		sdf.applyPattern("dd:MM:yyyy''HH:mm:ss");
		return sdf.format(date);
		
		
		
		
	}

	
	
	
	
}
