package com.ibm.springcisdemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibm.springcisdemo.models.CISCustomer;
import com.ibm.springcisdemo.models.Customer;


@Repository
public interface CustomerSQLRepository extends JpaRepository<Customer, Long> {
	

//	
//	@Query(value = "{'address.type': ?0}", fields = "{'addresses' : 0}")
//    Customer findByAddressType(String type);
//	
//	@Query(value = "{'cis-customer.email': ?0}", fields = "{'email' : 0}")
//	List<Customer> findCustomerByEmail(String email);
//	
//
//	
}

