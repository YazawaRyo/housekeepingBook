package com.example.domain.mapper;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.Infrastructure.ViewEntity;
import com.example.domain.model.Disbursement;

@Mapper
public interface DisbursementMapper {

	List<ViewEntity> selectALLData(LocalDate from, LocalDate to);

	void insertDisbursement(Disbursement disbursement);

	void dropNumber(Integer number);

}