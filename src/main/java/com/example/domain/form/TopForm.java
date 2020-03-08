package com.example.domain.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class TopForm {

	@NotNull
	@Min(1)
	private Integer year = 0;
	@NotNull
	@Min(1)
	@Max(12)
	private Integer month = 0;

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

}