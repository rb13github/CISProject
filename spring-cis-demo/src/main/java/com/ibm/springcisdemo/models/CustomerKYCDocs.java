package com.ibm.springcisdemo.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
//import org.springframework.data.annotation.Id;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
//import org.springframework.data.annotation.

@Entity
@Table(name="CustomerKYCDocs")
public class CustomerKYCDocs {
	@Id
	@Column(name = "KYC_Id")
	public String kyc_Id;
	
	

	@Column(name = "Accepted")
	public boolean accepted;
	
//	   @ManyToOne(fetch = FetchType.LAZY, optional = false)
//	    @JoinColumn(name = "customerId", nullable = false)
//	    @JsonIgnore
	    private Customer customer;
		
		public Customer getCustomer() {
			return customer;
		}
		public void setCustomer(Customer customer) {
			this.customer = customer;
		}
	
	public String getKyc_Id() {
		return kyc_Id;
	}
	public void setKyc_Id(String kyc_Id) {
		this.kyc_Id = kyc_Id;
	}

	public boolean isAccepted() {
		return accepted;
	}
	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}



}
