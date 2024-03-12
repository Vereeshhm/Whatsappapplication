package com.example.Whatsappapplication.ServiceResponse;

import com.fasterxml.jackson.annotation.JsonProperty;

//import com.fasterxml.jackson.annotation.JsonProperty;



public class WhatsaAppResponse {
  //  private ResponseObject responseObject;
     // private Status status;

//    public ResponseObject getResponseObject() {
//        return responseObject;
//    }
//
//    @JsonProperty("responseObject")
//    public void setResponseObject(ResponseObject responseObject) {
//        this.responseObject = responseObject;
//    }

//    public Status getStatus() {
//        return status;
//    }
//
//    @JsonProperty("status")
//    public void setStatus(Status status) {
//        this.status = status;
//    }
////
////    // Optional: You can override the toString() method to print the object.
//    @Override
//    public String toString() {
//        return "WhatsaAppResponse{" +
////                "responseObject=" + responseObject +
//                ", status=" + status +
//                '}';
//    }

	
//	public void setStatus(com.example.Whatsappapplication.ServiceResponse.Status status2) {
//		
//	}
	
	
	
	
    private int code;
    private String desc;
    private String status;

//    public Status(String code, String desc) {
//        this.code = code;
//        this.desc = desc;
//    }


	
	


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
 