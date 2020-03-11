package com.example.domain.service;

import org.springframework.stereotype.Service;

import com.example.domain.mapper.MemberMapper;
import com.example.domain.model.Member;

@Service
public class MemberRegistrationService {

	private final MemberMapper memberRegistrationMapper;

	public MemberRegistrationService(MemberMapper memberRegistrationMapper) {
		this.memberRegistrationMapper = memberRegistrationMapper;
	}

	public void insertMember(Member member) {

		memberRegistrationMapper.insertMember(member);

	}

}
