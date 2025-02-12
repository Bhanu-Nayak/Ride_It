package com.rideit.pojo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.rideit.enumclass.BookingStatus;
import com.rideit.enumclass.PaymentStatus;

//package com.example.bikerental.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//@Data
@Entity
@Table(name = "bookings")
public class Booking extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @ManyToOne
    @JoinColumn(name = "bike_id", nullable = false)
    private Bike bike;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus paymentStatus = PaymentStatus.PENDING; // pending, paid
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus bookingStatus = BookingStatus.ACTIVE; // active, completed, cancelled

    @Column(nullable = false)
    private Boolean deliveryRequested = false;

    @Column(precision = 10, scale = 2)
    private BigDecimal lateFee;

    @Column(precision = 10, scale = 2)
    private BigDecimal extensionFee;

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public Bike getBike() {
		return bike;
	}

	public void setBike(Bike bike) {
		this.bike = bike;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public BookingStatus getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(BookingStatus bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public Boolean getDeliveryRequested() {
		return deliveryRequested;
	}

	public void setDeliveryRequested(Boolean deliveryRequested) {
		this.deliveryRequested = deliveryRequested;
	}

	public BigDecimal getLateFee() {
		return lateFee;
	}

	public void setLateFee(BigDecimal lateFee) {
		this.lateFee = lateFee;
	}

	public BigDecimal getExtensionFee() {
		return extensionFee;
	}

	public void setExtensionFee(BigDecimal extensionFee) {
		this.extensionFee = extensionFee;
	}

	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Booking(Long bookingId, Bike bike, Customer customer, LocalDateTime startTime, LocalDateTime endTime,
			BigDecimal totalAmount, PaymentStatus paymentStatus, BookingStatus bookingStatus, Boolean deliveryRequested,
			BigDecimal lateFee, BigDecimal extensionFee) {
		super();
		this.bookingId = bookingId;
		this.bike = bike;
		this.customer = customer;
		this.startTime = startTime;
		this.endTime = endTime;
		this.totalAmount = totalAmount;
		this.paymentStatus = paymentStatus;
		this.bookingStatus = bookingStatus;
		this.deliveryRequested = deliveryRequested;
		this.lateFee = lateFee;
		this.extensionFee = extensionFee;
	}

  
//    ids
//    private LocalDateTime startTime;
//    private Bike bike;	
//    private Long bookingId;
//    private String bookingStatus = "active"; 
}

