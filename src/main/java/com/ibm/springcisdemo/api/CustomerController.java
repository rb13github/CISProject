package com.ibm.springcisdemo.api;



import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

import com.ibm.springcisdemo.repository.AddressSQLRepository;
import com.ibm.springcisdemo.repository.CustomerSQLRepository;


import io.swagger.annotations.Api;

import com.ibm.springcisdemo.models.Address;
import com.ibm.springcisdemo.models.Customer;


@RestController
//@RequestMapping(value={"/","/cis/customer"})
@RequestMapping(value={"/customer"})
@CrossOrigin(origins="*")
@Api(value="CIS", description="CIS Springboot SQL CRUD Application")
public class CustomerController {

	//private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
	@GetMapping(value = "/hello")
    public String greet() {
    	return "Welcome to CIS!!!";
    }
	
@Autowired
CustomerSQLRepository customerRepository;

@Autowired
AddressSQLRepository  addressSQLRepository;


@PostMapping(value="/create",headers="Accept=application/json")
 public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
	
	//System.out.println(" Request recieved Customer=" + customer);

	
	List<Address> newaddressList=new ArrayList<Address> ();
	for(Address addr:customer.getAddresses())
	{
		
		addr.setCustomer(customer);
		newaddressList.add(addr);
		System.out.println(" Request recieved Customer's Address=");
		
	}
	customer.setAddresses(newaddressList);
	
//	System.out.println(" Address after adding customer="+customer.getAddresses());
	
	//Timestamp timestamp = new Timestamp(System.currentTimeMillis());  
			int nextrandom=ThreadLocalRandom.current().nextInt();
			LocalDate today= LocalDate.now();
			String tokenid=today.toString()+"-"+String.valueOf(nextrandom);
			
			System.out.println(" Today's date="+today + " next Random Number"+nextrandom + " New tokenid No.="+tokenid);
						
			customer.setToken(tokenid);
			
			customer.setStart_Date(LocalDateTime.now());
			
			customer.setStatus("Initial");
			
			customer.setUserId(customer.getPrimary_Email());
			
	customerRepository.save(customer);
	
	for(Address addr:customer.getAddresses())
	{
		addr.setCustomer(customer);
	addressSQLRepository.save(addr);
	
	}
	return new ResponseEntity<Customer>(HttpStatus.OK);
}

@PutMapping(value="update/{custid}",headers="Accept=application/json")
public ResponseEntity<Customer> updateCustomer(@RequestBody Customer changecustomer, @PathVariable String custid) {
	Optional<Customer> customer = customerRepository.findById(Long.parseLong(custid));
	
	Customer customertemp= new Customer();
	if( customer != null && customer.isPresent()) {
		customertemp.setCustomerId(Long.parseLong(custid));
		
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
		
		
		customerRepository.save(customertemp);
	    return new ResponseEntity<Customer>(HttpStatus.OK);
	}
	else
	{
		System.out.println("custoemr Not found,No Update");
		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	}
    
    
    
}


@DeleteMapping(value="delete/{custid}",headers="Accept=application/json")
public ResponseEntity<Customer> deleteCustomer(@PathVariable String custid) {
	
Optional<Customer> customer = customerRepository.findById(Long.parseLong(custid));
	
	
	if( customer != null && customer.isPresent()) {
		customerRepository.deleteById(Long.parseLong(custid));
	    return new ResponseEntity<Customer>(HttpStatus.OK);
	}
	else{
		System.out.println("custoemr Not found,No Delete");
		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	}
    
	
   
}

@GetMapping(value="/search",headers="Accept=application/json")
public List<Customer> findAllUser() {
 // LOGGER.info("All cis-customer search");
	
	List<Customer> result = new ArrayList<Customer>();
	Iterable<Customer> custIterable=customerRepository.findAll();
    for (Customer cust:custIterable) {
        result.add(cust);
    }
	return result;
	}

@GetMapping(value="/search/{custid}",headers="Accept=application/json")
public Optional<Customer> findByid( @PathVariable String custid) {
 // LOGGER.info("All cis-customer search");
	return customerRepository.findById(Long.parseLong(custid));
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