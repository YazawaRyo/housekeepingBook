package com.example.domain.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Infrastructure.ViewEntity;
import com.example.domain.mapper.ViewMapper;

@Service
public class ViewService {

	private final ViewMapper viewMapper;

	public ViewService(ViewMapper viewMapper) {
		this.viewMapper = viewMapper;
	}

	public List<ViewEntity> selectALLData(LocalDate from, LocalDate to) {

		List<ViewEntity> entity = viewMapper.selectALLData(from, to);

		return entity;

	}

}
