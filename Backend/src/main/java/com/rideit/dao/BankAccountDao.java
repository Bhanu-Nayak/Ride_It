package com.rideit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rideit.pojo.BankAccount;
import com.rideit.pojo.Owner;

public interface BankAccountDao extends JpaRepository<BankAccount, Long> {
	public List<BankAccount> findByOwner(Owner owner);
}
