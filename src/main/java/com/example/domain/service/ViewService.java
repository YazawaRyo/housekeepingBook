package com.example.domain.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Infrastructure.ViewEntity;
import com.example.domain.mapper.DisbursementMapper;

@Service
public class ViewService {

	private final DisbursementMapper disbursementRegistrationMapper;

	public ViewService(DisbursementMapper disbursementRegistrationMapper) {
		this.disbursementRegistrationMapper = disbursementRegistrationMapper;
	}

	public List<ViewEntity> selectALLData(LocalDate from, LocalDate to) {

		List<ViewEntity> entity = disbursementRegistrationMapper.selectALLData(from, to);

		return entity;

	}

}
