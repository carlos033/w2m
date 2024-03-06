/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.w2m.domain.serviceimpl;

import java.util.Collections;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.w2m.application.adapter.AdministratorAdapter;
import com.w2m.application.service.JwtUserService;
import com.w2m.infrastructure.entity.Administrator;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class JwtUserServiceImpl implements JwtUserService{
	private static final String USERNAME_NOT_FOUND = "User not found with identifier: ";
	private final AdministratorAdapter adapter;

	@Override
	public UserDetails loadUserByUsername(String userName) {
		Administrator administrator = adapter.findById(userName)
				.orElseThrow(() -> new UsernameNotFoundException(USERNAME_NOT_FOUND + userName));
		return new User(administrator.getUsername(), administrator.getPassword(),
				Collections.emptyList());
	}

}
