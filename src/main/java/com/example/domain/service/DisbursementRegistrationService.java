package com.example.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Infrastructure.UserEntity;
import com.example.domain.mapper.DisbursementRegistrationMapper;
import com.example.domain.mapper.UserMapper;
import com.example.domain.model.Disbursement;

@Service
public class DisbursementRegistrationService {

	private final UserMapper userMapper;
	private final DisbursementRegistrationMapper disbursementRegistrationMapper;

	public DisbursementRegistrationService(UserMapper userMapper,
			DisbursementRegistrationMapper disbursementRegistrationMapper) {
		this.userMapper = userMapper;
		this.disbursementRegistrationMapper = disbursementRegistrationMapper;
	}

	public List<UserEntity> selectAllUser() {

		List<UserEntity> list = userMapper.selectAllUser();

		return list;
	}

	public void insertDisbursement(Disbursement disbursement) {

		disbursementRegistrationMapper.insertDisbursement(disbursement);

	}

}
