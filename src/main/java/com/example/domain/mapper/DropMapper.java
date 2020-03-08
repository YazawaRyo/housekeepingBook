package com.example.domain.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DropMapper {

	void dropNumber(Integer number);

}