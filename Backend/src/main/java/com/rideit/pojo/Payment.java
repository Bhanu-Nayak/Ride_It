package com.rideit.pojo;

import java.math.BigDecimal;

//package com.example.bikerental.entity;

//import lombok.Data;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

//@Data
@Entity
@Table(name = "payments")
public class Payment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @OneToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;
    @Column(nullable = false)
    private String paymentStatus = "pending";

    @Column(precision = 10, scale = 2)
    private BigDecimal ownerPayout;

    @Column(precision = 10, scale = 2)
    private BigDecimal platformFee;
    @Column(name="razerpayId")
    private String razerpayId;
	public Long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
	public Booking getBooking() {
		return booking;
	}
	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public BigDecimal getOwnerPayout() {
		return ownerPayout;
	}
	public void setOwnerPayout(BigDecimal ownerPayout) {
		this.ownerPayout = ownerPayout;
	}
	public BigDecimal getPlatformFee() {
		return platformFee;
	}
	public void setPlatformFee(BigDecimal platformFee) {
		this.platformFee = platformFee;
	}
	public String getRazerpayId() {
		return razerpayId;
	}
	public void setRazerpayId(String razerpayId) {
		this.razerpayId = razerpayId;
	}
    

}
