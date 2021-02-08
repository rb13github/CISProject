package com.ibm.springcisdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.springcisdemo.service.MyUserDetailsService;
//import com.ibm.springcisdemo.service.UserService;

@RequestMapping("/hello")
@RestController
public class HelloController {

	@Autowired
	MyUserDetailsService myUserDetailsService;
	
    @GetMapping("user")
    public String helloUser() {
    	UserDetails userDetails= myUserDetailsService.loadUserByUsername("user1");
    	
    	///to check the password
    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = "password";//Normally enter by the user
		String encodedPassword = passwordEncoder.encode(password);

		System.out.println();
		System.out.println("Password is         : " + password);
		System.out.println("Encoded Password is : " + userDetails.getPassword());//password stored in DB
		//System.out.println("Encoded Password is : " + encodedPassword);
		//System.out.println("Invalid Password is : " + encodedPassword + "junk");
		//System.out.println();

		
		boolean isPasswordMatch = passwordEncoder.matches(password, userDetails.getPassword());
		//boolean isPasswordMatch = passwordEncoder.matches(password, encodedPassword);
		System.out.println("Password : " + password + "   isPasswordMatch    : " + isPasswordMatch);
    	
		///to check the password warning 
        return "Hello User :"+userDetails.getPassword();
    }

    @GetMapping("admin")
    public String helloAdmin() {
        return "Hello Admin";
    }

    @GetMapping("genpass")
    public String genpassword() {
    	
    	int i = 0;
		while (i < 5) {
			String password = "password";
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(password);

			System.out.println("loop"+i+ " hashed="+hashedPassword);
			i++;
		}
    	
        return "Hello Admin";
    }
    
}