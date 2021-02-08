package com.ibm.springcisdemo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

//import com.ibm.bootcamp.entity.User;
import com.ibm.springcisdemo.entity.User;
import com.ibm.springcisdemo.repository.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {

	
	@Autowired
    private UserRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
    	System.out.println("in the MyUserDetailsService.loadUserByUsername:"+username);
    	Optional<User> user = userRepository.findByUsername(username);
    	
    	System.out.println("In the MyUserDetailsService.loadUserByUsername user name from DB:"+user.get().getUsername());
    	System.out.println("In the MyUserDetailsService.loadUserByUsername user name from DB:"+user.get().getUsername());System.out.println("In the MyUserDetailsService.loadUserByUsername user pass from DB:"+user.get().getPassword());
    	if (!user.isPresent()) {
    		System.out.println("In the MyUserDetailsService.loadUserByUsername user name from DB:"+user.get().getUsername());System.out.println("In the MyUserDetailsService.loadUserByUsername user not found:");
            throw new UsernameNotFoundException(username);
        }
        return user.get();
    }
    
}
