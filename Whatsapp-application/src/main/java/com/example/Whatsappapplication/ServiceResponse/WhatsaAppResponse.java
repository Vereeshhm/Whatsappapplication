package com.example.Whatsappapplication.ServiceResponse;

import com.fasterxml.jackson.annotation.JsonProperty;

//import com.fasterxml.jackson.annotation.JsonProperty;



public class WhatsaAppResponse {

	
	
	
	
    private int code;
    private String desc;
    private String status;



	

	public int getCode() {
        return code;
    }

    public String getStatus() {
		return status;
	}
    @JsonProperty("status")
	public void setStatus(String status) {
		this.status = status;
	}

	@JsonProperty("code")
    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    @JsonProperty("desc")
    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "status{" +
                "code=" + code +
                ", desc='" + desc + '\'' +
                ", status='" + status + '\'' +
                
                '}';
    }
   






}
 