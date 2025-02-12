package com.rideit.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rideit.customException.ResourceNotFoundException;
import com.rideit.dao.AdminDao;
import com.rideit.dao.BikeDao;
import com.rideit.dao.BookingDao;
import com.rideit.dao.CustomerDao;
import com.rideit.dao.PhotoByStringDao;
import com.rideit.dao.UserEntityDao;
import com.rideit.dto.BikePhotosDto;
import com.rideit.dto.BikeResponseDto;
import com.rideit.dto.BookingRequestDto;
import com.rideit.dto.CustomerLoginRequestDto;
import com.rideit.dto.CustomerLoginResponseDto;
import com.rideit.dto.GetCustomerResponseDto;
import com.rideit.dto.RequestCustomerDto;
import com.rideit.enumclass.PaymentStatus;
import com.rideit.enumclass.Role;
import com.rideit.enumclass.Status;
import com.rideit.pojo.Admin;
import com.rideit.pojo.Bike;
import com.rideit.pojo.Booking;
import com.rideit.pojo.Customer;
import com.rideit.pojo.PhotosByString;
import com.rideit.pojo.UserEntity;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class CustomerService implements ICustomerService{
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private BikeDao bikeDao;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private UserEntityDao userEntityDao;
	@Autowired
	private PhotoByStringDao photoDao;
	@Autowired
	private IPhotoService profilePhotoService;
	@Autowired
	private BookingDao bookingDao;
	@Autowired
	private AdminDao  adminDao;
//	private Integer hours=0;

	@Override
	public String registerCustomer(RequestCustomerDto customer) {
		customer.setPassword(encoder.encode(customer.getPassword()));
		Customer cust = mapper.map(customer, Customer.class);
		cust.setRole(Role.valueOf(customer.getRole().toUpperCase()));
		customerDao.save(cust);
		return "Customer register successfully";
	}
	@Override
	public String updateCustomer(Long cusId, RequestCustomerDto customer) {
		Customer cust = customerDao.findById(cusId).orElseThrow(()->new ResourceNotFoundException("customer not found!"));
		mapper.map(customer, cust);
		customerDao.save(cust);
		return "customer details update";
	}

	@Override
	public List<BikeResponseDto> searchBike() {
		List<Bike> bikeList = bikeDao.findAllBikesWithPhotos();
		List<BikeResponseDto> listBikeDto = new ArrayList<>();
		for(Bike bike: bikeList) {
			List<BikePhotosDto> photoDto = new ArrayList<>();
			BikeResponseDto bikeDto = mapper.map(bike, BikeResponseDto.class);
			bikeDto.setOwnerName(bike.getOwner().getName());
			for(PhotosByString photo: bike.getBikeImage()) {
				photoDto.add(mapper.map(photo, BikePhotosDto.class));
			}
			bikeDto.setBikeImage(photoDto);
			listBikeDto.add(bikeDto);
		}
		return listBikeDto;
	}
	
	@Override
	public CustomerLoginResponseDto loginCustomer(CustomerLoginRequestDto customer) {
		Customer cust = customerDao.findByEmail(customer.getEmail())
				.orElseThrow(()->new ResourceNotFoundException("Invalid Email !!!"));
		if(encoder.matches(customer.getPassword(), cust.getPassword())) {
			if(userEntityDao.existsByUserEntityIdEmailAndUserEntityIdRole(cust.getEmail(), cust.getRole())) {
				return mapper.map(cust, CustomerLoginResponseDto.class);
			}
			UserEntity user = new UserEntity(cust.getName(), cust.getEmail(), cust.getPassword(), cust.getRole());
			userEntityDao.save(user);
		}
		else {
			new ResourceNotFoundException("Invalid password");
		}
		return mapper.map(cust, CustomerLoginResponseDto.class);
	}
	@Override
	public String uploadProfilePhoto(Long id, MultipartFile file) throws IOException {
		
		return profilePhotoService.uploadImageToFileSystem(id, file, "CUSTOMER");
	}
	@Override
	public GetCustomerResponseDto getCustomerById(Long custId) {
		Customer customerDetails = customerDao.findById(custId).orElseThrow(()->new ResourceNotFoundException("Customer not Foudn"));
		System.out.println("CustomerDetails "+customerDetails.getProfilePhoto());
		return mapper.map(customerDetails , GetCustomerResponseDto.class);
	}
	@Override
	public String deleteCustomer(Long custId) {
		Customer customer = customerDao.findById(custId).orElseThrow(( )->new ResourceNotFoundException("Customer not found"));
		customerDao.delete(customer);
		return "Customer Delete Successfully";
	}
	@Override
	public String bookABike(BookingRequestDto bookingBikeDto) {
		Customer custDetails = customerDao.findById(bookingBikeDto.getCustId()).orElseThrow(( )->new ResourceNotFoundException("Customer not found"));
		Bike bikeDetails = bikeDao.findById(bookingBikeDto.getBikeId()).orElseThrow(( )->new ResourceNotFoundException("Bike not found"));
		bikeDetails.setStatus(Status.BOOKED);
		Booking bookedDetails = mapper.map(bookingBikeDto, Booking.class);
		bookedDetails.setCustomer(custDetails);
		bookedDetails.setBike(bikeDetails);
//		bookedDetails.setStartTime(book);
		bookedDetails.setPaymentStatus(PaymentStatus.SUCCESS);
		bookingDao.save(bookedDetails);
		return "bike booked successfully";
	}

}
