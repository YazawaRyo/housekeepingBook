package com.example.domain.service;

import org.springframework.stereotype.Service;

import com.example.Infrastructure.LoginEntity;
import com.example.domain.mapper.MemberMapper;

@Service
public class LoginService {

	private final MemberMapper memberMapper;

	public LoginService(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}

	public LoginEntity selectUser(String id, String password) {

		LoginEntity entity = memberMapper.selectUser(id, password);

		return entity;

	}

}
