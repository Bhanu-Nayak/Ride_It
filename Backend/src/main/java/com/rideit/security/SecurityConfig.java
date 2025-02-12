package com.rideit.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.AllArgsConstructor;

@EnableWebSecurity // - required in earlier spring sec versions -enabled by default
@Configuration // equivalent to bean config xml file
@AllArgsConstructor
public class SecurityConfig {

	private PasswordEncoder encoder;
	private CustomJwtAuthenticationFilter jwtFilter;
	@Bean
	public SecurityFilterChain authorizeRequests(HttpSecurity http) throws Exception {
		http.csrf(customizer -> customizer.disable())
				.authorizeHttpRequests(request -> request
						.requestMatchers(
								 "/owner/addowner","/owner/login","/customer/loginCustomer","/customer/searchbike",
								"/customer/registerCustomer", "/v*/api-doc*/**","/customer/getallphoto","/owner/uploadimageforbike",
								"/swagger-ui/**","/owner/getallowner", "/auth/login", "/auth/protected", "/admin/getallowner"
								,"/admin/getallbikes", "/owner/uploadprofilephoto", "/customer/uploadprofilephoto","/auth/signin","/owner/deleteowner/{onwerId}"
								,"/admin/getallcustomer", "/admin/register","/admin/login","/admin/verifyowner/{oid}","/admin/verifycustomer/{cid}")
						.permitAll().requestMatchers(HttpMethod.OPTIONS).permitAll()

						.requestMatchers("/customer/updateCustomer/{custId}", "/customer/searchbike", "/customer/getcustomer/{custId}","/customer/bookabike").hasAuthority("CUSTOMER")
//						.requestMatchers("/products/add", "/products/delete").hasAuthority("ADMIN")
						.requestMatchers("/owner/updateowner/{OwnerId}",
								"/owner/changestatus/{ownerId}/{status}",
								"/owner/uploadimagelink",
								"/owner/uploadimage",
								"/owner/addbike",
								"/owner/updatebikestatus/{status}/{id}",
								"/owner/getowner/{OwnerId}",
								"/onwer/getbike/bikeId",
								"/owner/getAllbikes",
								"/owner/deleteBike/{bikeid}","/owner/updatebikelocation", "/owner/editbike/{bikeId}").hasAuthority("OWNER") 	
						.requestMatchers(
								"/admin/getallowner","/admin/getallbikes","/admin/getallcustomer").hasAuthority("ADMIN")
						.anyRequest()
						.authenticated())

				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}


	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}
