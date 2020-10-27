package com.ibm.springcisdemo.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class User {
	
	@Id
	@Field(value = "UserId") 
	private String userId;
	@Field(value = "Password") 
	private String password;
	
	@Field(value = "Enabled") 
	private boolean enabled;
	@Field(value = "Locked") 
	private boolean locked;
	@Field(value = "LastLogin") 
	private Date  lastLogin;
	@Field(value = "InvalidAttempts") 
	private int invalidAttempts;

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public boolean isLocked() {
		return locked;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public int getInvalidAttempts() {
		return invalidAttempts;
	}
	public void setInvalidAttempts(int invalidAttempts) {
		this.invalidAttempts = invalidAttempts;
	}


}
