package com.ibm.springcisdemo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class CISCustomerKYCDocs {
	@Id
	@Field(value = "KYC_Id")
	public String kyc_Id;
	@Field(value = "Customer_Id")
	public String customer_Id;
	@Field(value = "Accepted")
	public boolean accepted;
	
	public String getKyc_Id() {
		return kyc_Id;
	}
	public void setKyc_Id(String kyc_Id) {
		this.kyc_Id = kyc_Id;
	}
	public String getCustomer_Id() {
		return customer_Id;
	}
	public void setCustomer_Id(String customer_Id) {
		this.customer_Id = customer_Id;
	}
	public boolean isAccepted() {
		return accepted;
	}
	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}



}
