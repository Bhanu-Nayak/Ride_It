package com.rideit.pojo;

import com.rideit.enumclass.Role;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;


@MappedSuperclass

public abstract class User extends BaseEntity{

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String contactNum;

    @Column(nullable = false, unique = true)
    private String email;


    @Enumerated(EnumType.STRING)
    private Role role;
    


    @Column(nullable = false)
    private Boolean verifiedStatus = false;
    
    @Column(nullable = false)
    private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactNum() {
		return contactNum;
	}

	public void setContactNum(String contactInfo) {
		this.contactNum = contactInfo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Boolean getVerifiedStatus() {
		return verifiedStatus;
	}

	public void setVerifiedStatus(Boolean verifiedStatus) {
		this.verifiedStatus = verifiedStatus;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
    
    
}
