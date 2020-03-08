package com.example.domain.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.model.Member;

@Mapper
public interface MemberRegistrationMapper {

	void insertMember(Member member);

}