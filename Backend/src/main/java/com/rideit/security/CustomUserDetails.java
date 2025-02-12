package com.rideit.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.rideit.pojo.*;


public class CustomUserDetails implements UserDetails {
	private UserEntity user;
	
	public CustomUserDetails(UserEntity entity) {
		this.user=entity;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		List<SimpleGrantedAuthority> list = List.of(new SimpleGrantedAuthority(
				user.getRole().toString()));
		System.out.println(list);
		return list;
	}

	@Override
	public String getPassword() {

		return user.getPassword();
	}

	@Override
	public String getUsername() {

		return user.getEmail();
	}

	public UserEntity getUser() {
		return user;
	}
	

	

}
