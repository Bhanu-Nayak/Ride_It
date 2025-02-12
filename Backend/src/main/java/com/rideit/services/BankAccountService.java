package com.rideit.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rideit.customException.ResourceNotFoundException;
import com.rideit.dao.BankAccountDao;
import com.rideit.dao.OwnerDao;
import com.rideit.dto.BankAccountDto;
import com.rideit.pojo.BankAccount;
import com.rideit.pojo.Owner;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class BankAccountService implements IBankAccountService{
	@Autowired
	ModelMapper modelmapper;
	@Autowired
	public OwnerDao ownerDao;
	@Autowired
	public BankAccountDao bankAccountDao;
	@Override
	public String addBankAccount(BankAccountDto accountDto) {
		Long oId=accountDto.getOwner() ;
		Owner o =ownerDao.findById(oId).orElseThrow(()-> new ResourceNotFoundException("invalid owner id, so such owner exists! ")) ;
		BankAccount account=modelmapper.map(accountDto,BankAccount.class );
		o.addAccount(account);	
		return "BankAccount added successflly " ;
	}

	@Override
	public String removeBankAccount(Long oId,Long BankAccId)
	{
		BankAccount bAcc= bankAccountDao.findById(BankAccId).orElseThrow(()-> new ResourceNotFoundException("invalid BankAccount id..opps! ")) ;
		Owner o =ownerDao.findById(oId).orElseThrow(()-> new ResourceNotFoundException("invalid owner id..opps! ")) ;
		o.removeAccount(bAcc);
		return "BankAccount removed successflly " ;
	}

}
