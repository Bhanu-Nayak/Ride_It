package com.rideit.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bankaccounts")
public class BankAccount extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bankAccId;
	@Column(name="ifsccode", nullable = false)
	private String ifscCode;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="owner_id")
	private Owner owner;
	
	public Long getBankAccId() {
		return bankAccId;
	}
	public void setBankAccId(Long bankAccId) {
		this.bankAccId = bankAccId;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public Owner getOwner() {
		return owner;
	}
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	
	
}
