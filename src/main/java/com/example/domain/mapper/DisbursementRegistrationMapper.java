package com.example.domain.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.model.Disbursement;

@Mapper
public interface DisbursementRegistrationMapper {

	void insertDisbursement(Disbursement disbursement);

}