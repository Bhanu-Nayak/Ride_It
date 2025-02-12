package com.rideit.pojo;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass

public abstract class BaseEntity {
	@CreationTimestamp
	private LocalDateTime createdAt;
	@UpdateTimestamp
    private LocalDateTime updatedAt;
	public BaseEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
}
