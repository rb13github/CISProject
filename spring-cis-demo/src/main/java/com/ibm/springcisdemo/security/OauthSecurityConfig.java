package com.ibm.springcisdemo.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.ibm.springcisdemo.service.CustomOIDCService;


/*Add the Oauth dependency in pom.xml
 * Create the Client Secret on the google cloud
 * Add the OAuth Properties on the application property file
 * create custom SecurityConfig (OauthSecurityConfig) extends from WebSecurityConfigurerAdapter
 *Create Custom class (CustomOIDCService) by extending it from OidcUserService
 * override configure(HttpSecurity http) of WebSecurityConfigurerAdapter to Authlogin() 
 * and OidcUserService for implementation of Oauth open ID connect provider as per the configuraiton in applicaiton
 * properties
 * 
 * for generating client iD refer https://developers.google.com/identity/one-tap/web/guides/get-google-api-clientid
 */
@EnableWebSecurity(debug=true)
public class OauthSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

	/*
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/welcome", "/oauth2/authorization/**").permitAll() 
		.anyRequest().authenticated()
        .and()
        .rememberMe().tokenValiditySeconds(60 * 60 * 24 * 7).key("RAJESHWAR").and()
        .oauth2Login(oauth2Login -> oauth2Login.redirectionEndpoint(RedirectEndpoint -> RedirectEndpoint.baseUri("/orders") ));
	}
	*/
	
	/*
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.httpBasic()
			  .and()
			    .csrf().disable()
			    .authorizeRequests()
			        .anyRequest()
			            .authenticated().and().oauth2Login();
	}
	*/
	
	
	 
	
	@Autowired
	CustomOIDCService customOIDCService;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		System.out.println("Inside OauthSecurityConfig.configure------ ");
		http.authorizeRequests().antMatchers("/welcome").permitAll() 
		.anyRequest().authenticated()
        .and()
        .rememberMe().tokenValiditySeconds(60*2).key("MySecurityKey").and() //2 mins expiry
        .oauth2Login()
        .redirectionEndpoint()
        .and()
        .userInfoEndpoint()
        .oidcUserService(customOIDCService)
        ;
		
		
	}
	
	 @Override
		@Bean
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}
	

	/*
	 * @Override public void
	 * setAuthenticationConfiguration(AuthenticationConfiguration
	 * authenticationConfiguration) { // TODO Auto-generated method stub
	 * super.setAuthenticationConfiguration(authenticationConfiguration); }
	 */

}