package com.rideit.services;

import java.util.List;

import com.rideit.bike.BikeDto;
import com.rideit.dto.AdminLoginReqDto;
import com.rideit.dto.AdminLoginResponseDto;
import com.rideit.dto.AdminRegisterDto;
import com.rideit.dto.GetCustomerResponseDto;
import com.rideit.dto.OwnerWithBikeReqDto;
import com.rideit.pojo.Admin;

public interface IAdminService {
	List<OwnerWithBikeReqDto> getAllOwners();
	List<BikeDto> getAllBike();
	List<GetCustomerResponseDto> getAllCustomer();
//	String registerAdmin(Admin registerDto);
	//
	AdminLoginResponseDto loginAdmin(AdminLoginReqDto adminDto);
	String toggleOwnerVerifiedStatus(Long oid);
	String toggleCustomerVerifiedStatus(Long cid);
	String registerAdmin(AdminRegisterDto adminDto);
	String updateAdmin(Long adminId, AdminRegisterDto adminDto);
}
