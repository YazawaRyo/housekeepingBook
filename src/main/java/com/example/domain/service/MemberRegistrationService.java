package com.example.domain.service;

import org.springframework.stereotype.Service;

import com.example.domain.mapper.MemberRegistrationMapper;
import com.example.domain.model.Member;

@Service
public class MemberRegistrationService {

	private final MemberRegistrationMapper memberRegistrationMapper;

	public MemberRegistrationService(MemberRegistrationMapper memberRegistrationMapper) {
		this.memberRegistrationMapper = memberRegistrationMapper;
	}

	public void insertMember(Member member) {

		memberRegistrationMapper.insertMember(member);

	}

}
