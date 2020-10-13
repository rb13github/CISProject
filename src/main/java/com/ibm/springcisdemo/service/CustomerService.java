package com.ibm.springcisdemo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.springcisdemo.repository.AddressSQLRepository;
import com.ibm.springcisdemo.repository.CustomerSQLRepository;
import com.ibm.springcisdemo.models.*;
@Service
@Transactional
public class CustomerService {

	
@Autowired
CustomerSQLRepository repo;

@Autowired
AddressSQLRepository  addressSQLRepository;

	 public List<Customer> listAll() {
		 
		 List<Customer> result = new ArrayList<Customer>();
			Iterable<Customer> custIterable=repo.findAll();
		    for (Customer cust:custIterable) {
		        result.add(cust);
		    }
	        return result;
	    }
	    
	   public boolean update(Customer changecustomer)
	   {
		   
		   Optional<Customer> customer = findById((changecustomer.getCustomerId()));
			
			Customer customertemp= new Customer();
			if( customer != null && customer.isPresent()) {
				customertemp.setCustomerId((changecustomer.getCustomerId()));
				
				customertemp.setTitle(changecustomer.getTitle());
				customertemp.setFirstName(changecustomer.getFirstName());
				customertemp.setLastName(changecustomer.getLastName());
				customertemp.setMiddleName(changecustomer.getMiddleName());
				customertemp.setDob(changecustomer.getDob());	
				customertemp.setPrimary_Email(changecustomer.getPrimary_Email());
				//As userid would be the primary email id there fore it will also updated.
				customertemp.setUserId(changecustomer.getPrimary_Email());
				customertemp.setSecondary_Email(changecustomer.getSecondary_Email());
				
				customertemp.setPrimary_Phone(changecustomer.getPrimary_Phone());
				customertemp.setSecondary_Phone(changecustomer.getSecondary_Phone());
				
				//Timestamp timestamp = new Timestamp(System.currentTimeMillis());  
				int nextrandom=ThreadLocalRandom.current().nextInt();
				LocalDate today= LocalDate.now();
				String token=today.toString()+"-"+String.valueOf(nextrandom);
				
				System.out.println(" Today's date="+today + " next Random Number"+nextrandom + " New token No.="+token);
				
				//form id will remains unchange
				//customertemp.setForm_id(form_id);
				
				customertemp.setToken(token);
				
				//customertemp.setStart_Date(LocalDateTime.now());
				
				//customertemp.setStatus("Initial");
				
				customertemp.setStatus(changecustomer.getStatus());
				customertemp.setCategory_Id(changecustomer.getCategory_Id());
				
				customertemp.setAddresses(changecustomer.getAddresses());
				//customertemp.setCustomerKYCDoc(changecustomer.getCustomerKYCDoc());
				
				
				save(customertemp,false);
			    return true;
			}
			else
			{
				System.out.println("custoemr Not found,No Update");
				return false;
			}
		    
		    
		   
	   }
	     
	    public void save(Customer customer,boolean isnew) {
	    	
	    	
	    	if (isnew)
	    	{
	    	List<Address> newaddressList=new ArrayList<Address> ();
	    	if(null!=customer.getAddresses()){
	    		for(Address addr:customer.getAddresses()){
	    		addr.setCustomer(customer);
	    		newaddressList.add(addr);
	    		System.out.println(" Request recieved Customer's Address=");
	    		
	    		}
	    		customer.setAddresses(newaddressList);
	    	}

	    	
//	    	System.out.println(" Address after adding customer="+customer.getAddresses());
	    	
	    	//Timestamp timestamp = new Timestamp(System.currentTimeMillis());  
	    			int nextrandom=ThreadLocalRandom.current().nextInt();
	    			LocalDate today= LocalDate.now();
	    			String tokenid=today.toString()+"-"+String.valueOf(nextrandom);
	    			
	    			System.out.println(" Today's date="+today + " next Random Number"+nextrandom + " New tokenid No.="+tokenid);
	    						
	    			customer.setToken(tokenid);
	    			
	    			customer.setStart_Date(LocalDateTime.now());
	    			
	    			customer.setStatus("Initial");
	    			
	    			customer.setUserId(customer.getPrimary_Email());
	    			
	    	
	    	
	    	if(newaddressList.size()>0){
	    		for(Address addr:customer.getAddresses()){
	    			addr.setCustomer(customer);
	    		   addressSQLRepository.save(addr);
	    		}
	    	}
	    	}
	    	
	    	//System.out.println("CustomerService.save() Saving the Customer :"+Customer.getId());
	        repo.save(customer);
	    }
	     
	    public Optional<Customer> findById(Long id) {
	        //return repo.findById(id).get();
	    	return repo.findById(id);
	    }
	     
	    public void delete(Long id) {
	        repo.deleteById(id);
	    }
	
	
}
