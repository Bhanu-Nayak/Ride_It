package com.rideit.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rideit.bike.BikeDto;
import com.rideit.customException.ResourceNotFoundException;
import com.rideit.dao.AdminDao;
import com.rideit.dao.BikeDao;
import com.rideit.dao.CustomerDao;
import com.rideit.dao.OwnerDao;
import com.rideit.dao.UserEntityDao;
import com.rideit.dto.AdminLoginReqDto;
import com.rideit.dto.AdminLoginResponseDto;
import com.rideit.dto.AdminRegisterDto;
import com.rideit.dto.BikeForOwnerDto;
import com.rideit.dto.GetCustomerResponseDto;
import com.rideit.dto.OwnerWithBikeReqDto;
import com.rideit.enumclass.Role;
import com.rideit.pojo.Admin;
import com.rideit.pojo.Bike;
import com.rideit.pojo.Customer;
import com.rideit.pojo.Owner;
import com.rideit.pojo.UserEntity;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class AdminService implements IAdminService {
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private OwnerDao ownerDao;
	@Autowired
	private BikeDao bikeDao;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private UserEntityDao userEntityDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private PasswordEncoder encoder;
	@Override
	public List<OwnerWithBikeReqDto> getAllOwners() {
		List<Owner> olist=ownerDao.findAllOwnerWithBikes();
//		System.out.println(olist);
//		if(olist!=null){
//			System.out.println(olist);
//		}
		List<OwnerWithBikeReqDto> ownerDtoList = new ArrayList<>();
		for(Owner ownerDetails: olist) {	
			System.out.println(ownerDetails);
			List<BikeForOwnerDto> bikeForOwnerDto = new ArrayList<>();
			OwnerWithBikeReqDto ownerDto = mapper.map(ownerDetails, OwnerWithBikeReqDto.class);
			ownerDto.setOwnerName(ownerDetails.getName());
			
			for(Bike b: ownerDetails.getBikes()) {
				BikeForOwnerDto dto = mapper.map(b, BikeForOwnerDto.class);
				bikeForOwnerDto.add(dto);
			}
			ownerDto.setBikes(bikeForOwnerDto);
			ownerDtoList.add(ownerDto);
		
		}
		return ownerDtoList;
	}

	@Override
	public List<BikeDto> getAllBike() {
		List<Bike> bikeList = bikeDao.findAll();
		List<BikeDto> bikeListDto = new ArrayList<>();
		for(Bike bike: bikeList) {
			BikeDto bikeDto = mapper.map(bike, BikeDto.class);
			bikeDto.setOwnerId(bike.getOwner().getOwnerId());
			bikeListDto.add(bikeDto);
		}
		return bikeListDto;
	}

	@Override
	public List<GetCustomerResponseDto> getAllCustomer() {
		List<Customer> customerList = customerDao.findAll();
		List<GetCustomerResponseDto> customerDtoList = new ArrayList<>();
		for(Customer customer: customerList) {
			GetCustomerResponseDto customerDto = mapper.map(customer, GetCustomerResponseDto.class);
//			customerList.add(customer);
//			customerDto.setProfilePhoto(customer);;
			customerDtoList.add(customerDto);
		}
		return customerDtoList;
	}

//	@Override
//	public String registerAdmin(Admin registerDto) {
//		registerDto.setPassword(encoder.encode(registerDto.getPassword()));
//		adminDao.save(registerDto);
//		return "Admin registerd";
//	}
	
	@Override
	public String registerAdmin(AdminRegisterDto adminDto) {
		adminDto.setPassword(encoder.encode(adminDto.getPassword()));
		
		Admin admin = mapper.map(adminDto, Admin.class);
		admin.setRole(Role.valueOf(adminDto.getRole().toUpperCase()));
		adminDao.save(admin);
		return "Admin register successfuully";
		
	}
	
	@Override
	public AdminLoginResponseDto loginAdmin( AdminLoginReqDto adminDto) 
	{
		Admin admin=adminDao.findByEmail(adminDto.getEmail()); //.orElseThrow(()->new ResourceNotFoundException("Invalid Email !!!"));
		
		if(encoder.matches(adminDto.getPassword(),admin.getPassword()))
		{
			// valid admin
			// check if already added in userentity table(by email and role)
			if( userEntityDao.existsByUserEntityIdEmailAndUserEntityIdRole(admin.getEmail(), admin.getRole()))
			{
				// indicates it exists in userentitytable
				return mapper.map(admin, AdminLoginResponseDto.class);
			}
			
			UserEntity userEntity= new UserEntity(admin.getName(),admin.getEmail(),admin.getPassword(), admin.getRole());
			userEntityDao.save(userEntity);
			
		}
		else {
			new ResourceNotFoundException("Invalid ADMIN");
		}
		return mapper.map(admin, AdminLoginResponseDto.class);
		
	}
	@Override
	public String toggleOwnerVerifiedStatus(Long oid) {
		Owner owner=ownerDao.findById(oid).orElseThrow(()->new ResourceNotFoundException("invalid owner id")) ;
		
		if(owner.getVerifiedStatus()==true)
		{
			owner.setVerifiedStatus(false);
			return "Owner Not verified";
		}else
		{
			owner.setVerifiedStatus(true);
			return "Owner verified ";
		}
		
		
	}
	@Override
	public String toggleCustomerVerifiedStatus(Long cid) {
		Customer customer=customerDao.findById(cid).orElseThrow(()->new ResourceNotFoundException("invalid Customer id")) ;
		
		if(customer.getVerifiedStatus()==true)
		{
			customer.setVerifiedStatus(false);
			return "Customer Not verified";
		}else
		{
			customer.setVerifiedStatus(true);
			return "Customer verified ";
		}
		
		
	}
	@Override
	public String updateAdmin(Long adminId, AdminRegisterDto adminDto) {
		Admin admin=adminDao.findById(adminId).orElseThrow(()->new ResourceNotFoundException("invalid Admin")) ;
		mapper.map(adminDto, admin);
		adminDao.save(admin);
		return "Admin "+admin.getName()+" updated";
	}

}
