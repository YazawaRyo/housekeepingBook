package com.example.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.domain.mapper.DisbursementMapper;

@Service
public class DropService {

	private final DisbursementMapper disbursementRegistrationMapper;

	public DropService(DisbursementMapper disbursementRegistrationMapper) {
		this.disbursementRegistrationMapper = disbursementRegistrationMapper;
	}

	public void dropNumber(List<Integer> numberList) {
		for (Integer number : numberList) {
			disbursementRegistrationMapper.dropNumber(number);
		}
	}

}
