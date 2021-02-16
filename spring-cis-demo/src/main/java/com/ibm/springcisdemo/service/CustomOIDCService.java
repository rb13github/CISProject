package com.ibm.springcisdemo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

@Service
public class CustomOIDCService extends OidcUserService {
	
	private OidcUser user;
	private String providerToken;
	private String serviceToken=null;
	
	
	
	

	@Override
	public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
		user = super.loadUser(userRequest);
		providerToken = user.getAccessTokenHash();
		//TODO: Check how to get name from Oidc
		//String name = userRequest.getAdditionalParameters().get("name").toString();
		System.out.println("accessToken:--------------> " + providerToken);
		//System.out.println("name:--------------> " + name);
		if(serviceToken == null && !"".equals(serviceToken)) {
			//generate new token
			serviceToken = "ST" + (new Date()).getDate() + Math.random();
			System.out.println("Service token generated: " + serviceToken);
		}
		return super.loadUser(userRequest);
	}
	
	
	public boolean isTokenValid(String reqToken) {
		if(reqToken != null && !"".equals(reqToken)) {
			if(reqToken.equals(providerToken)) {
				return true;
			} else {
				return false;
			}
		}else return false;
	}

	public String getServiceToken() {
		return serviceToken;
	}
	
	public void invalidateServiceToken() {
		serviceToken=null;
	}
	
}
