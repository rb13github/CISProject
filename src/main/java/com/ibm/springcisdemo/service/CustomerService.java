package com.ibm.springcisdemo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
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
