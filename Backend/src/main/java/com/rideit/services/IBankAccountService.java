package com.rideit.services;

import com.rideit.dto.BankAccountDto;

public interface IBankAccountService {
	String addBankAccount(BankAccountDto account);
	String removeBankAccount(Long oId, Long BankAccId);
}
