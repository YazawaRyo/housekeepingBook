package com.example.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.domain.mapper.DropMapper;

@Service
public class DropService {

	private final DropMapper dropMapper;

	public DropService(DropMapper dropMapper) {
		this.dropMapper = dropMapper;
	}

	public void dropNumber(List<Integer> numberList) {
		for (Integer number : numberList) {
			dropMapper.dropNumber(number);
		}
	}

}
