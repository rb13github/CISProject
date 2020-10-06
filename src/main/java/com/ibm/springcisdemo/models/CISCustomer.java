package com.ibm.springcisdemo.models;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
	
	import org.springframework.data.annotation.Id;
	import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "cis-customerv1")
public class CISCustomer {

	
	
	@Id //It denotes that this is primary key
	@Field(value = "_id")  //It maps JSON property with MongoDB Document Field
	private String customerId;
	
	@Field(value = "UserId") 
	private String userId;
	
	@Field(value = "FirstName") 
	private String firstName;
	@Field(value = "MiddleName") 
	private String middleName;
	@Field(value = "LastName") 
	private String lastName;
	@Field(value = "Primary_Email") 
	private String primary_Email;
	@Field(value = "Secondary_Email") 
	private String secondary_Email;
	@Field(value = "Primary_Phone") 
	private String primary_Phone;
	@Field(value = "Secondary_Phone") 
	private String secondary_Phone;
	@Field(value = "Title") 
	private String title;
	@Field(value = "Category_Id") 
	private String category_Id;
	@Field(value = "DOB") 
	private LocalDate dob;
	@Field(value = "Status") 
	private String status;
	@Field(value = "Start_Date") 
	private LocalDateTime start_Date;
	
	@Field(value = "Token") 
	private String token;
	@Field(value = "Form_id") 
	private String form_id;

    private List<CISAddress> addresses;
   // private User user;
    private CustomerKYCDocs customerKYCDoc;
   // private SecretQA secretQA;
    
	/*Constructors*/
	public CISCustomer() {

	}
    
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
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

	public CustomerKYCDocs getCustomerKYCDoc() {
		return customerKYCDoc;
	}

	public void setCustomerKYCDoc(CustomerKYCDocs customerKYCDoc) {
		this.customerKYCDoc = customerKYCDoc;
	}

//	public SecretQA getSecretQA() {
//		return secretQA;
//	}
//
//	public void setSecretQA(SecretQA secretQA) {
//		this.secretQA = secretQA;
//	}

	
	public List<CISAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<CISAddress> addresses) {
		this.addresses = addresses;
	}



	
	


}
