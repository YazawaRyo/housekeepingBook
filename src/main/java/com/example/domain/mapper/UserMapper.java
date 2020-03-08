package com.example.domain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.Infrastructure.UserEntity;

@Mapper
public interface UserMapper {

	List<UserEntity> selectAllUser();

}