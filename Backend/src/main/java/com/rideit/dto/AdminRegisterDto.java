package com.rideit.dto;

import com.rideit.enumclass.Role;



public class AdminRegisterDto {

	  
	    private String name;

	    
	    private String contactNum;

	
	    private String email;


	   
	    private String role;
	
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

		public void setContactNum(String contactNum) {
			this.contactNum = contactNum;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	    
	    
}