package com.example.domain.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.Infrastructure.LoginEntity;

@Mapper
public interface LoginMapper {

	LoginEntity selectUser(String id, String password);

}