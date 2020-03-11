package com.example.domain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.Infrastructure.LoginEntity;
import com.example.Infrastructure.UserEntity;
import com.example.domain.model.Member;

@Mapper
public interface MemberMapper {

	LoginEntity selectUser(String id, String password);

	List<UserEntity> selectAllUser();

	void insertMember(Member member);

}