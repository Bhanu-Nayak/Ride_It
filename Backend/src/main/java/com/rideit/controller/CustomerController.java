package com.rideit.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rideit.dto.ApiResponse;
import com.rideit.dto.BookingRequestDto;
import com.rideit.dto.CustomerLoginRequestDto;
import com.rideit.dto.CustomerLoginResponseDto;
import com.rideit.dto.RequestCustomerDto;
import com.rideit.security.JwtUtils;
import com.rideit.services.CustomerService;
import com.rideit.services.ICustomerService;
import com.rideit.services.IPhotoByStringService;



@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:8080/swagger-ui")
public class CustomerController {
	@Autowired
	private ICustomerService custService;
	@Autowired
	private AuthenticationManager authMgr;
	@Autowired
	private IPhotoByStringService photoService;
	@Autowired
	private JwtUtils jwtutils;
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/registerCustomer")
	public ResponseEntity<?> registerCustomer(@RequestBody RequestCustomerDto cust){
			try {
				return ResponseEntity.ok(new ApiResponse(custService.registerCustomer(cust)));
						
			}
			catch(RuntimeException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
			}
	}
	
	@PutMapping("/updateCustomer/{custId}")
	public ResponseEntity<?> updateCustomer(@RequestBody RequestCustomerDto cust, @PathVariable Long custId){
		try {
			return ResponseEntity.ok(new ApiResponse(custService.updateCustomer(custId, cust)));
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
	}
	@GetMapping("/searchbike")
	public ResponseEntity<?> searchBike(){
		try {
			return ResponseEntity.ok(custService.searchBike());
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	
//	@GetMapping("/getallphoto")
//	public ResponseEntity<?> getAllPhoto(){
//		return ResponseEntity.ok(photoService.getAllPhotos());
//	}
	//test
//	@GetMapping("/searchimage")
//	public ResponseEntity<?> searchImage(){
//		try {
//			return ResponseEntity.ok(custService.searchImage());
//		}catch(RuntimeException e) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
//		}
//	}
	@PostMapping("/loginCustomer")
	public ResponseEntity<?> loginCustomer(@RequestBody CustomerLoginRequestDto customer){
		try {
			CustomerLoginResponseDto resp = custService.loginCustomer(customer);
			UsernamePasswordAuthenticationToken token = 
					new UsernamePasswordAuthenticationToken(customer.getEmail(),
							customer.getPassword());
						Authentication verifiedToken = authMgr.authenticate(token);
							resp.setJwt(jwtutils.generateJwtToken(verifiedToken));
			return ResponseEntity.ok(resp);
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
	}
	@PostMapping("/uploadprofilephoto/{id}")
	public ResponseEntity<?> uploadProfilePhoto(@PathVariable Long id, @RequestParam("image") MultipartFile file){
		   try {
			   	
	            return ResponseEntity.ok(new ApiResponse(customerService.uploadProfilePhoto(id, file)));
	        } catch (IOException e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Failed to upload image"));
	        }
	}
	
	@GetMapping("/getcustomer/{custId}")
	public ResponseEntity<?> getCustomerById(@PathVariable Long custId)
	{
		try {
			return ResponseEntity.ok(customerService.getCustomerById(custId));
					
			
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	
	@DeleteMapping("/deletecustomer/{custId}")
	public ResponseEntity<?> deleteCustomerById(@PathVariable Long custId){
		try {
			return ResponseEntity.ok(new ApiResponse(customerService.deleteCustomer(custId)));
		}
		catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	@PostMapping("/bookabike")
	public ResponseEntity<?> bookABike(@RequestBody BookingRequestDto bookingDto){
		try {
			return ResponseEntity.ok(new ApiResponse(customerService.bookABike(bookingDto)));
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	//bike details
	//customer details
	//
}
