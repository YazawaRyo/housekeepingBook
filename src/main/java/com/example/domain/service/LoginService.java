package com.example.domain.service;

import org.springframework.stereotype.Service;

import com.example.Infrastructure.LoginEntity;
import com.example.domain.mapper.LoginMapper;

@Service
public class LoginService {

	private final LoginMapper loginMapper;

	public LoginService(LoginMapper loginMapper) {
		this.loginMapper = loginMapper;
	}

	public LoginEntity selectUser(String id, String password) {

		LoginEntity entity = loginMapper.selectUser(id, password);

		return entity;

	}

}
