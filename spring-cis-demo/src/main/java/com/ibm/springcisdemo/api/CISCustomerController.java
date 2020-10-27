package com.ibm.springcisdemo.api;



import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ibm.springcisdemo.repository.CustomerRepositoryMongo;

import io.swagger.annotations.Api;

import com.ibm.springcisdemo.models.CISCustomer;


@RestController
//@RequestMapping(value={"/","/cis/customer"})
@RequestMapping(value={"/cis/customer"})
@CrossOrigin(origins="*")
@Api(value="CIS", description="CIS Springboot CRUD Application with MongoDB")
public class CISCustomerController {

	//private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
	@GetMapping(value = "/hello")
    public String greet() {
    	return "Welcome to CIS v1!!!";
    }
	
@Autowired
CustomerRepositoryMongo cisCustomerRepository;


@PostMapping(value="/create",headers="Accept=application/json")
 public ResponseEntity<CISCustomer> createCISCustomer(@RequestBody CISCustomer customer) {
	
	
	//Timestamp timestamp = new Timestamp(System.currentTimeMillis());  
			int nextrandom=ThreadLocalRandom.current().nextInt();
			LocalDate today= LocalDate.now();
			String tokenid=today.toString()+"-"+String.valueOf(nextrandom);
			
			System.out.println(" Today's date="+today + " next Random Number"+nextrandom + " New tokenid No.="+tokenid);
						
			customer.setToken(tokenid);
			
			customer.setStart_Date(LocalDateTime.now());
			
			customer.setStatus("Initial");
			
			customer.setUserId(customer.getPrimary_Email());
			
	cisCustomerRepository.save(customer);
	return new ResponseEntity<CISCustomer>(HttpStatus.OK);
}

@PutMapping(value="update/{custid}",headers="Accept=application/json")
public ResponseEntity<CISCustomer> updateCISCustomer(@RequestBody CISCustomer changecustomer, @PathVariable String custid) {
	Optional<CISCustomer> customer = cisCustomerRepository.findById(custid);
	
	CISCustomer customertemp= new CISCustomer();
	if( customer != null && customer.isPresent()) {
		customertemp.setCustomerId(custid);
		
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
		customertemp.setCustomerKYCDoc(changecustomer.getCustomerKYCDoc());
		
		
		cisCustomerRepository.save(customertemp);
	    return new ResponseEntity<CISCustomer>(HttpStatus.OK);
	}
	else
	{
		System.out.println("custoemr Not found,No Update");
		return new ResponseEntity<CISCustomer>(HttpStatus.NOT_FOUND);
	}
    
    
    
}


@DeleteMapping(value="delete/{custid}",headers="Accept=application/json")
public ResponseEntity<CISCustomer> deleteCISCustomer(@PathVariable String custid) {
	
Optional<CISCustomer> customer = cisCustomerRepository.findById(custid);
	
	
	if( customer != null && customer.isPresent()) {
		cisCustomerRepository.deleteById(custid);
	    return new ResponseEntity<CISCustomer>(HttpStatus.OK);
	}
	else{
		System.out.println("custoemr Not found,No Delete");
		return new ResponseEntity<CISCustomer>(HttpStatus.NOT_FOUND);
	}
    
	
   
}

@GetMapping(value="/search",headers="Accept=application/json")
public List<CISCustomer> findAllUser() {
 // LOGGER.info("All cis-customer search");
	return cisCustomerRepository.findAll();
	}

@GetMapping(value="/search/{custid}",headers="Accept=application/json")
public Optional<CISCustomer> findByid( @PathVariable String custid) {
 // LOGGER.info("All cis-customer search");
	return cisCustomerRepository.findById(custid);
	}


	
}


/*
 * 
  {
        "id": "cust101",
        "mobile": "99999999",
        "name": "cust101",
        "addresses": [
            
            {   "addressId":"cust101",
                "addressLine1":"a1",
                 "addressLine2":"a2",
                  "addressLine3":"a3",
                   "city":"c1",
                    "state":"s1",
                     "country":"in",
                      "pincode":"411052",
                      "type":"permanent"
                  
            
            }
            ]
    }
    */



/*
 * 
  {
        "id": "cust102",
        "mobile": "99999999",
        "name": "cust102",
        "addresses": [
            
            {   "addressId":"cust102",
                "addressLine1":"a1",
                 "addressLine2":"a2",
                  "addressLine3":"a3",
                   "city":"c1",
                    "state":"s1",
                     "country":"in",
                      "pincode":"411052",
                      "type":"permanent"
                  
            
            },
            {   "addressId":"cust102local",
                "addressLine1":"a1",
                 "addressLine2":"a2",
                  "addressLine3":"a3",
                   "city":"c1",
                    "state":"s1",
                     "country":"in",
                      "pincode":"411052",
                      "type":"local"
                  
            
            }
            ]
    }
    */