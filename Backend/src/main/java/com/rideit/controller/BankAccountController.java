package com.rideit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rideit.dto.ApiResponse;
import com.rideit.dto.BankAccountDto;
import com.rideit.services.IBankAccountService;

@RestController
@RequestMapping("/bankaccounts")
public class BankAccountController {
	@Autowired
	public IBankAccountService bankAccountService;
	
	@PostMapping
	public ResponseEntity<?> addBankAccount(@RequestBody BankAccountDto account)//MOdel model
	{//model.add model.add
		try
		{
			
		
		String s=bankAccountService.addBankAccount(account);
		return ResponseEntity.ok(new ApiResponse(s));
		}
		catch(RuntimeException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));

			
		}
		
	}
	
	@DeleteMapping("/{oid}/{bankid}")
	public ResponseEntity<?> removeBankAcount(@PathVariable Long oid, @PathVariable Long bankid)
	{
		try
		{
		String s =bankAccountService.removeBankAccount(oid, bankid);
		return ResponseEntity.ok(new ApiResponse(s));
		}
		catch(RuntimeException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));

			
		}
		
	}
}
