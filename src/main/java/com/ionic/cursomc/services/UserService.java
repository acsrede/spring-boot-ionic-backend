package com.ionic.cursomc.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.ionic.cursomc.security.UserSS;

public class UserService {

	public static UserSS authenicated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
	
}
