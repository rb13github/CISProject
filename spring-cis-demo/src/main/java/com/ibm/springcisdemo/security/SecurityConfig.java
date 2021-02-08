package com.ibm.springcisdemo.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.ibm.springcisdemo.filters.JwtRequestFilter;
import com.ibm.springcisdemo.repository.UserRepo;
import com.ibm.springcisdemo.service.MyUserDetailsService;
import com.ibm.springcisdemo.utils.JwtUtil;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static java.lang.String.format;
import org.slf4j.Logger;
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserRepo userRepo;
    
    
//    @Autowired
//	private AuthenticationManager authenticationManager;

    @Autowired
	private JwtRequestFilter jwtRequestFilter;
	
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    
    public SecurityConfig(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {  
    	
    	// Enable CORS and disable CSRF
    	httpSecurity = httpSecurity.cors().and().csrf().disable();

        // Set session management to stateless
//        httpSecurity = httpSecurity
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and();
        
        httpSecurity.authorizeRequests()
 // Our public endpoints
    .antMatchers("/hello/**").permitAll()
    .antMatchers("/authenticate").permitAll()
    .antMatchers("/h2-console/**").permitAll()
    .antMatchers("/web/**").permitAll()
    .antMatchers("/error").permitAll()
 // Our private endpoints
    .anyRequest().authenticated().and()
    
    ////////////////// used if want to have login page, but if want API to be secure then not require
	.formLogin()
		//.loginPage("/login")
	.permitAll().and()
	//.logout().permitAll().and()
    ///////////////////////
	
	////for single request without session mgmt for API calls
	//.exceptionHandling().and().sessionManagement()
	//.sessionCreationPolicy(SessionCreationPolicy.STATELESS)	
	;
    
    //to apply filter 
    httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
}
    
//    @Override
//    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
//        configurer
//                .inMemory()
//                .withClient(clientId)
//                .secret(passwordEncoder.encode(clientSecret))
//                .authorizedGrantTypes(grantType)
//                .scopes(scopeRead, scopeWrite)
//                .resourceIds(resourceIds);
//    }

    //-------------------https://spring.io/guides/gs/securing-web/
//    @Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//			.authorizeRequests()
//				.antMatchers("/", "/hello").permitAll() //allow access without authentication
//				.anyRequest().authenticated()
//				.and()
//			.formLogin()
//				.loginPage("/login")
//				.permitAll()
//				.and()
//			.logout()
//				.permitAll();
//	}

//	@Bean
//	@Override
//	public UserDetailsService userDetailsService() {
//		UserDetails user =
//			 User.withDefaultPasswordEncoder()
//				.username("user")
//				.password("password")
//				.roles("USER")
//				.build();
//
//		return new InMemoryUserDetailsManager(user);
//	}
	
	//-------------------------https://www.toptal.com/spring/spring-security-tutorial

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	System.out.println("in the SecurityConfig.configure:"+auth);
    	
    	auth.userDetailsService(username -> userRepo
            .findByUsername(username)
            .orElseThrow(
                () -> new UsernameNotFoundException(
                    format("User: %s, not found", username)
                )
            ))
    	.passwordEncoder(passwordEncoder())
    	;
    	
    //	auth.userDetailsService(myUserDetailsService);
    //	auth.passwordEncoder(passwordEncoder());
    	
    }
    
    
/// following are the hashed value for "password" use the same in login
//$2a$10$7bvyCx6OGn7rQnwgVnvbDOWagw5s3T.oslj5VYvyJWQcPvnBZ8uFa
//$2a$10$E4FZjdsa8ohDmh6v6wwLlOd5dPbpPHhGCR7RrMdTXCHg2s2ZGcD4e
//$2a$10$6ULHPNijESBKLBgjogwWt.rnu4By3nWrnSThnWqwMunQkzuVCds3m

    // Details omitted for brevity
	
//    @Bean
//  //  @Autowired
//    public PasswordEncoder  passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }

  //  SuppressWarnings("deprecation")
//    @Bean
//    public static NoOpPasswordEncoder passwordEncoder() {
//    return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//    }
    //This AuthenticationManager is implicit in the before spring 2.0 and after 2.0 onwards it has to set explicitly
    @Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
//    
  @Bean
 	public PasswordEncoder passwordEncoder(){
  	System.out.println("in the SecurityConfig.passwordEncoder");
 		PasswordEncoder encoder = new BCryptPasswordEncoder();
 		return encoder;
 	}
    
//    @Bean
//   	public PasswordEncoder passwordEncoder(){
//    	System.out.println("in the SecurityConfig.passwordEncoder");
//   		PasswordEncoder encoder = new BCryptPasswordEncoder();
//   		return encoder;
//   	}
}



//===================
//$2a$10$ZLhnHxdpHETcxmtEStgpI./Ri1mksgJ9iDP36FmfMdYyVg9g0b2dq
//
//There are three fields separated by $:
//
//    The “2a” represents the BCrypt algorithm version
//    The “10” represents the strength of the algorithm
//    The “ZLhnHxdpHETcxmtEStgpI.” part is actually the randomly generated salt. Basically, the first 22 characters are salt. The remaining part of the last field is the actual hashed version of the plain text
//
//Also, be aware that the BCrypt algorithm generates a String of length 60



//==============================
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//	
//	 @Override
//	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//	        // TODO configure authentication manager
//	    }
//
//	    @Override
//	    protected void configure(HttpSecurity http) throws Exception {
//	        // TODO configure web security
//	    }
//}
