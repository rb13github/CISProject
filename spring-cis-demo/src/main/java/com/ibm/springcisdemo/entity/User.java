package com.ibm.springcisdemo.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="CISUSER")
public class User implements UserDetails, Serializable {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="name")
	private String username;
	
	@Column(name="password")
	private String password;

	@Transient
	 private boolean enabled = true;
	
	public User() {
        this.username = "default";
        this.password = "defaultpass";
        this.enabled = true;
    }
	 public User(String username, String password) {
	        this.username = username;
	        this.password = password;
	        this.enabled = true;
	    }
	 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String name) {
		this.username = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public String getUsername() {
//		// TODO Auto-generated method stub
//		return "default_return_getUsername()";
//	}

	@Override
	public boolean isEnabled() {
	
		return enabled;
	}

	

}
