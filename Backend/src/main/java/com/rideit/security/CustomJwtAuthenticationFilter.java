package com.rideit.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomJwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtils utils;
	

	@Autowired
	private Request request1;

	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		if(uri.contains("owner")) {
			request1.setRequest("OWNER");
		}
		else if(uri.contains("customer")) {
			request1.setRequest("CUSTOMER");
		}
		else {
			request1.setRequest("ADMIN");
		}
		String authHeader = request.getHeader("Authorization");
		if (authHeader != null && authHeader.startsWith("Bearer ")) {

			String jwt = authHeader.substring(7);
			Authentication authentication = 
					utils.populateAuthenticationTokenFromJWT(jwt);
	
			SecurityContextHolder.getContext().setAuthentication(authentication);
			System.out.println("saved auth token in sec ctx");
		}
		filterChain.doFilter(request, response);

	}

}
