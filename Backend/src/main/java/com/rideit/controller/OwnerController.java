package com.rideit.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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

import com.rideit.bike.BikeDto;
import com.rideit.dto.ApiResponse;
import com.rideit.dto.LocationDto;
import com.rideit.dto.OwnerLoginRequesetDto;
import com.rideit.dto.OwnerLoginResponseDto;
import com.rideit.dto.OwnerRegisterDto;
import com.rideit.dto.UpdateBikeRequestDto;
import com.rideit.pojo.Owner;
import com.rideit.security.CustomUserDetails;
import com.rideit.security.JwtUtils;
import com.rideit.services.IOwnerService;
import com.rideit.services.PhotosByStringService;



@RestController
@RequestMapping("/owner")
@CrossOrigin(origins = "http://localhost/5173")
public class OwnerController {
	@Autowired
	private IOwnerService ownerService;
	@Autowired
	private AuthenticationManager authMgr;
	@Autowired
	private PhotosByStringService photoService;
	@Autowired
	private JwtUtils jwtutils;
	
	private ResponseCookie cookies;
	
	@PostMapping("/login")
	public ResponseEntity<?> OwnerLogin(@RequestBody OwnerLoginRequesetDto ownerDto)
	{
		try
		{
			OwnerLoginResponseDto resp= ownerService.loginOwner(ownerDto);
			UsernamePasswordAuthenticationToken token = 
			new UsernamePasswordAuthenticationToken(ownerDto.getEmail(),
					ownerDto.getPassword());
				Authentication verifiedToken = authMgr.authenticate(token);
				CustomUserDetails userDetails = (CustomUserDetails) verifiedToken.getPrincipal();
//				ResponseCookie jwtCookie = jwtutils.generateJwtCookie(userDetails);
				String jwtToken = jwtutils.generateJwtToken(verifiedToken); 
				resp.setJwt(jwtToken);
//				System.out.println(jwtCookie);
//					resp.setJwt(jwtCookie.toString());
				 return ResponseEntity.ok()
                         .body(resp);

			
		}catch(BadCredentialsException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse(e.getMessage()));
		}
		catch(RuntimeException e)
		{
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse(e.getMessage()));
			
		}
		
		
	}
	
	@GetMapping("/getallowner")
	public ResponseEntity<?> getAllOwners()
	{
		try
		{
			List<Owner> olist= ownerService.getAllOwnersDetails();
		return ResponseEntity.status(HttpStatus.FOUND).body(olist);
				
		}catch(RuntimeException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	
	
	@GetMapping("/getowner/{OwnerId}")
	public ResponseEntity<?> getOwnerDetails(@PathVariable Long OwnerId )
	{
		
		try
		{
			OwnerRegisterDto o =ownerService.getOwnerDetails(OwnerId);
			return ResponseEntity.status(HttpStatus.FOUND).body(o);
			
		}
		catch(RuntimeException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
		
		
	}
	//getbikebyownerid
	@PutMapping("/updateowner/{OwnerId}")
	
	public ResponseEntity<?> UpdateOwnerDetails(@RequestBody OwnerRegisterDto ownerdto, @PathVariable Long OwnerId)
	{
		try
		{
			String s =ownerService.updateOwnerDetails(ownerdto,OwnerId);
			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(s)) ;
			
		}catch(RuntimeException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
		
	}
	@PutMapping("/changestatus/{ownerId}/{status}")
	public ResponseEntity<?> toggleAvailability(@PathVariable Long ownerId, @PathVariable Boolean status)
	{
		try
		{
			String s =ownerService.toggleAvailabilty(status, ownerId);
			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(s));
			
		}catch(RuntimeException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));

		}
		
		
	}
	
	@PostMapping("/addowner")
	public ResponseEntity<?> registerOwner(@RequestBody OwnerRegisterDto ownerdetails){
		try {
			
			return ResponseEntity.ok(new ApiResponse(ownerService.registerOwner(ownerdetails)));
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
		
		
	}
	@PostMapping("/addbike")
	public ResponseEntity<?> addBike(@RequestBody BikeDto bikeDetails){
		try {
			return ResponseEntity.ok(new ApiResponse(ownerService.addBike(bikeDetails)));
		}catch(RuntimeException e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
		
	}
//	@GetMapping("/getallbike")
//	public ResponseEntity<?> getAllBike(){
//		return ResponseEntity.ok(ownerService.getAllBike());
//	}
	@GetMapping("/getbike/bikeId")
	public ResponseEntity<?> getBikeById(@RequestParam Long bikeId){
		try {
			return ResponseEntity.ok(ownerService.getBikeById(bikeId));
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
		
	}
	@GetMapping("/getAllbikes/{ownerId}")
	public ResponseEntity<?> getAllBikeByOwnerId(@PathVariable Long ownerId){
		try {
			return ResponseEntity.ok(ownerService.getBikeByOwnerId(ownerId));
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
		
	}
	@GetMapping("/updatebikestatus/{status}/{id}")
	public ResponseEntity<?> updateBikeStatus(@PathVariable String status, Long id){
		try {
			return ResponseEntity.ok(new ApiResponse(ownerService.updateBikeStatus(status, id)));
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
		
	}
	@DeleteMapping("/deleteBike/{bikeid}")
	public ResponseEntity<?> deleteById(@PathVariable Long bikeid){
		try {
			return ResponseEntity.ok(new ApiResponse(ownerService.deleteBike(bikeid)));
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
		
	}
	@PostMapping("/uploadprofilephoto")
	public ResponseEntity<?> uploadProfileImage(@RequestParam Long id, @RequestParam("image") MultipartFile file){
		   try {
			   	
	            return ResponseEntity.ok(new ApiResponse(ownerService.uploadProfilePhoto(id, file)));
	        } catch (IOException e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Failed to upload image"));
	        }
	}
	
	@PostMapping("/uploadimageforbike")
	public ResponseEntity<?> uploadBikeImage(@RequestParam Long id, @RequestParam("image") MultipartFile path) throws IOException{
		try {
			
			return ResponseEntity.ok(photoService.uploadImageToFileSystem(id, path));
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	@PostMapping("/updatebikelocation/{id}")
	public ResponseEntity<?> updateBikeLocation(@PathVariable Long id, @RequestBody LocationDto location){
		try {
			return ResponseEntity.ok(new ApiResponse(ownerService.updateLocation(id, location)));
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	@DeleteMapping("/deleteowner/{ownerId}")
	public ResponseEntity<?> deleteOwner(@PathVariable Long ownerId) {
		try {
			return ResponseEntity.ok(new ApiResponse(ownerService.deleteOwner(ownerId)));
		}
		catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
		
	}
	
	@PutMapping("/editbike/{bikeId}")
	public ResponseEntity<?> updateOwnerBike(@PathVariable Long bikeId, @RequestBody UpdateBikeRequestDto bikeRequestDto){
		try {
			return ResponseEntity.ok(new ApiResponse(ownerService.updateOwnerBike(bikeId, bikeRequestDto)));
		}
		catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	

	
}
