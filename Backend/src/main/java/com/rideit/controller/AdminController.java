package com.rideit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rideit.dto.AdminLoginReqDto;
import com.rideit.dto.AdminLoginResponseDto;
import com.rideit.dto.AdminRegisterDto;
import com.rideit.dto.ApiResponse;
import com.rideit.pojo.Admin;
import com.rideit.security.JwtUtils;
import com.rideit.services.IAdminService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {
	@Autowired
	private IAdminService adminService;
	@Autowired
	private AuthenticationManager authMgr;

	@Autowired
	private JwtUtils jwtutils;
	@GetMapping("/getallowner")
	public ResponseEntity<?> getAllOwner(){
		try {
			return ResponseEntity.ok(adminService.getAllOwners());
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
		
	}
	@GetMapping("/getallbikes")
	public ResponseEntity<?> getAllBikes(){
		try {
			return ResponseEntity.ok(adminService.getAllBike());
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	@GetMapping("/getallcustomer")
	public ResponseEntity<?> getAllCustomer(){
		try {
			return ResponseEntity.ok(adminService.getAllCustomer());
		}
		catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
//	@PostMapping("/register")
//	public ResponseEntity<?> registerAdmin(@RequestBody Admin adminDetails){
//		try {
//			return ResponseEntity.ok(adminService.registerAdmin(adminDetails));
//		}catch(RuntimeException e) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
//		}
//	}
	
	@PostMapping("/registeradmin")
	public ResponseEntity<?> registerCustomer(@RequestBody AdminRegisterDto admin){
			try {
				return ResponseEntity.ok(new ApiResponse(adminService.registerAdmin(admin)));
						
			}
			catch(RuntimeException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
			}
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> adminLogin(@RequestBody AdminLoginReqDto adminDto )
	{
		try
		{
			AdminLoginResponseDto adminResp= adminService.loginAdmin(adminDto);
			UsernamePasswordAuthenticationToken token = 
					new UsernamePasswordAuthenticationToken(adminDto.getEmail(),
							adminDto.getPassword());
			Authentication verifiedToken = authMgr.authenticate(token);
			adminResp.setJwt(jwtutils.generateJwtToken(verifiedToken));
			return ResponseEntity.ok(adminResp);
			
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}	
	}
	@PutMapping("/verifyowner/{oid}")
		
		public ResponseEntity<?> ToggleOwnerVerifiedStatus(@PathVariable Long oid)
		{
			try
			{
				String s =adminService.toggleOwnerVerifiedStatus(oid);
				return ResponseEntity.ok(s);
				
			}catch(RuntimeException e)
			{
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
	
			}
		}
	@PutMapping("/verifycustomer/{cid}")
	public ResponseEntity<?> ToggleCustomerVerifiedStatus(@PathVariable Long cid)
	{
		try
		{
			String s =adminService.toggleCustomerVerifiedStatus(cid);
			return ResponseEntity.ok(s);
			
		}catch(RuntimeException e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));

		}
	}
	@PutMapping("/updateadmin/{adminid}")
	public ResponseEntity<?> updateCustomer(@RequestBody AdminRegisterDto adminDto, @PathVariable Long adminid){
		try {
			return ResponseEntity.ok(new ApiResponse(adminService.updateAdmin(adminid, adminDto)));
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
	}
}
