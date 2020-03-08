package com.example.domain.mapper;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.Infrastructure.ViewEntity;

@Mapper
public interface ViewMapper {

	List<ViewEntity> selectALLData(LocalDate from, LocalDate to);

}