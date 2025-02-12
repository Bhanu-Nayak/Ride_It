package com.rideit.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.rideit.enumclass.BookingStatus;

public class BookingRequestDto {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long bookingId;

   
    private Long bikeId;

    
    private Long custId;


    private LocalDateTime startTime;


    private LocalDateTime endTime;


    private BigDecimal totalAmount;

//    private String paymentStatus; // pending, paid

    
    private BookingStatus bookingStatus; // active, completed, cancelled

    
//    private Boolean deliveryRequested;

    
    private BigDecimal lateFee;

    
    private BigDecimal extensionFee;


//	public Long getBookingId() {
//		return bookingId;
//	}
//
//
//	public void setBookingId(Long bookingId) {
//		this.bookingId = bookingId;
//	}


	public Long getBikeId() {
		return bikeId;
	}


	public void setBookingStatus(BookingStatus bookingStatus) {
		this.bookingStatus = bookingStatus;
	}


	public void setBikeId(Long bikeId) {
		this.bikeId = bikeId;
	}


	public Long getCustId() {
		return custId;
	}


	public void setCustId(Long custId) {
		this.custId = custId;
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

//
//	public String getPaymentStatus() {
//		return paymentStatus;
//	}
//
//
//	public void setPaymentStatus(String paymentStatus) {
//		this.paymentStatus = paymentStatus;
//	}

//
//	public String getBookingStatus() {
//		return bookingStatus;
//	}


//	public void setBookingStatus(String bookingStatus) {
//		this.bookingStatus = bookingStatus;
//	}


//	public Boolean getDeliveryRequested() {
//		return deliveryRequested;
//	}
//
//
//	public void setDeliveryRequested(Boolean deliveryRequested) {
//		this.deliveryRequested = deliveryRequested;
//	}


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
    
    
}
