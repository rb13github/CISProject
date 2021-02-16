package com.ibm.springcisdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import com.ibm.springcisdemo.service.CustomOIDCService;




@Controller
public class OauthSecurityController {

	
	@Autowired
	CustomOIDCService service;
	
	@GetMapping("/welcome")
	@ResponseBody
	public String welcome() {
		System.out.println("Inside welcome");
	      return "Welcome to online grocery store!";
	   }
	
	
	@GetMapping("/user")
	@ResponseBody
	public String user( ) {
		System.out.println("user->>>>>>>>>>>>>>>>>>>>>>>: " + getUser());
		String serviceToken = service.getServiceToken();
		String jSessionID = RequestContextHolder.currentRequestAttributes().getSessionId();
		return "Hello " + getUser() + "\n Service Token: " + serviceToken + "\n sessionID: " + jSessionID ;
	}
	
	String getUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("details " + auth.getDetails());
		return auth.getName();
		
	}

	
	
	
}




