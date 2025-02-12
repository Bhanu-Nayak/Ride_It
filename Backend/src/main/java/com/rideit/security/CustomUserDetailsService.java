package com.rideit.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rideit.dao.UserEntityDao;
import com.rideit.enumclass.Role;
import com.rideit.pojo.UserEntity;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private UserEntityDao userEntityRepository;
//	private UserEntityIdDao userEntityIdDao;
	private JwtUtils jwtUtils;
	private Request request;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		
		
		UserEntity user = userEntityRepository.
				//email customer
				findByUserEntityIdEmailAndUserEntityIdRole(email, Role.valueOf(request.getRequest()));
//		if(user == null) {
//			UserEntity user1 = userEntityRepository.findByUserEntityIdEmail(email);
////			UserEntity user2 = userEntityRepository.
////					//email customer
////					findByUserEntityIdEmailAndUserEntityIdRole(email, user1.getRole());
//			if(user1!=null) {
//				return new CustomUserDetails(user1);
//			}
//			
//			 
//		}
		return new CustomUserDetails(user);
	}
}
