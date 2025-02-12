package com.rideit.pojo;

//package com.example.bikerental.entity;

import jakarta.persistence.*;
//import lombok.Data;
import lombok.EqualsAndHashCode;

//@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "admins")
public class Admin extends User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;
	
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + "]";
	}
	public Long getAdminId() {
		return adminId;
	}
	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}
	
    
}
