package com.example.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Infrastructure.UserEntity;
import com.example.domain.mapper.DisbursementMapper;
import com.example.domain.mapper.MemberMapper;
import com.example.domain.model.Disbursement;

@Service
public class DisbursementRegistrationService {

	private final MemberMapper memberMapper;
	private final DisbursementMapper disbursementRegistrationMapper;

	public DisbursementRegistrationService(MemberMapper memberMapper,
			DisbursementMapper disbursementRegistrationMapper) {
		this.memberMapper = memberMapper;
		this.disbursementRegistrationMapper = disbursementRegistrationMapper;
	}

	public List<UserEntity> selectAllUser() {

		List<UserEntity> list = memberMapper.selectAllUser();

		return list;
	}

	public void insertDisbursement(Disbursement disbursement) {

		disbursementRegistrationMapper.insertDisbursement(disbursement);

	}

}
