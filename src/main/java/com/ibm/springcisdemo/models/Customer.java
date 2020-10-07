package com.ibm.springcisdemo.models;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
//import org.springframework.data.annotation.Id;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Id;
//import org.springframework.data.annotation.
import javax.persistence.OneToMany;

@Entity
@Table(name="RBCustomer")
public class Customer implements Serializable{

	
	
	@Id //It denotes that this is primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customerId")  
	private Long customerId;
	
	@Column(name = "userId") 
	private String userId;
	
	@Column(name = "firstName") 
	private String firstName;
	@Column(name = "middleName") 
	private String middleName;
	@Column(name = "lastName") 
	private String lastName;
	@Column(name = "primary_Email") 
	private String primary_Email;
	@Column(name = "secondary_Email") 
	private String secondary_Email;
	@Column(name = "primary_Phone") 
	private String primary_Phone;
	@Column(name = "secondary_Phone") 
	private String secondary_Phone;
	@Column(name = "title") 
	private String title;
	@Column(name = "category_Id") 
	private String category_Id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "dob") 
	private LocalDate dob;
	@Column(name = "status") 
	private String status;
	@Column(name = "start_Date") 
	private LocalDateTime start_Date;
	
	@Column(name = "token") 
	private String token;
	@Column(name = "form_id") 
	private String form_id;

	 @OneToMany(mappedBy = "customer", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	 @OnDelete(action = OnDeleteAction.CASCADE)
	 @JsonIgnore
	 private List<Address> addresses;
  //  private User user;
//	 @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private CustomerKYCDocs customerKYCDoc;
   // private SecretQA secretQA;
    
	/*Constructors*/
	public Customer() {

	}
    
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPrimary_Email() {
		return primary_Email;
	}

	public void setPrimary_Email(String primary_Email) {
		this.primary_Email = primary_Email;
	}

	public String getSecondary_Email() {
		return secondary_Email;
	}

	public void setSecondary_Email(String secondary_Email) {
		this.secondary_Email = secondary_Email;
	}

	public String getPrimary_Phone() {
		return primary_Phone;
	}

	public void setPrimary_Phone(String primary_Phone) {
		this.primary_Phone = primary_Phone;
	}

	public String getSecondary_Phone() {
		return secondary_Phone;
	}

	public void setSecondary_Phone(String secondary_Phone) {
		this.secondary_Phone = secondary_Phone;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory_Id() {
		return category_Id;
	}

	public void setCategory_Id(String category_Id) {
		this.category_Id = category_Id;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getStart_Date() {
		return start_Date;
	}

	public void setStart_Date(LocalDateTime start_Date) {
		this.start_Date = start_Date;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getForm_id() {
		return form_id;
	}

	public void setForm_id(String form_id) {
		this.form_id = form_id;
	}

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}

//	public CustomerKYCDocs getCustomerKYCDoc() {
//		return customerKYCDoc;
//	}
//
//	public void setCustomerKYCDoc(CustomerKYCDocs customerKYCDoc) {
//		this.customerKYCDoc = customerKYCDoc;
//	}

//	public SecretQA getSecretQA() {
//		return secretQA;
//	}
//
//	public void setSecretQA(SecretQA secretQA) {
//		this.secretQA = secretQA;
//	}

	
	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

//	@Override
//	public String toString() {
//		return "Customer [customerId=" + customerId + ", userId=" + userId + ", firstName=" + firstName
//				+ ", middleName=" + middleName + ", lastName=" + lastName + ", primary_Email=" + primary_Email
//				+ ", secondary_Email=" + secondary_Email + ", primary_Phone=" + primary_Phone + ", secondary_Phone="
//				+ secondary_Phone + ", title=" + title + ", category_Id=" + category_Id + ", dob=" + dob + ", status="
//				+ status + ", start_Date=" + start_Date + ", token=" + token + ", form_id=" + form_id + ", addresses="
//				+ addresses + "]";
//	}



	
	


}

