package com.rideit.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.rideit.dto.BikeResponseDto;
import com.rideit.dto.BookingRequestDto;
import com.rideit.dto.CustomerLoginRequestDto;
import com.rideit.dto.CustomerLoginResponseDto;
import com.rideit.dto.GetCustomerResponseDto;
import com.rideit.dto.RequestCustomerDto;

public interface ICustomerService {
	String registerCustomer(RequestCustomerDto customer);
	String updateCustomer(Long cusId, RequestCustomerDto customer);
	List<BikeResponseDto> searchBike();
	CustomerLoginResponseDto loginCustomer(CustomerLoginRequestDto customer);
	String uploadProfilePhoto(Long id, MultipartFile file)throws IOException;
//	String calculateAmount();
//	Customer 
	GetCustomerResponseDto getCustomerById(Long custId);
	String deleteCustomer(Long custId);
	String bookABike(BookingRequestDto bookingBikeDto);
}
