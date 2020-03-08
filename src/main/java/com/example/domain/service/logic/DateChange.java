package com.example.domain.service.logic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateChange {

	public static LocalDate getLastYmd(int year, int month) {

		LocalDate localDate = LocalDate.of(year, month, 1);
		localDate = localDate.plusMonths(1);
		localDate = localDate.minusDays(1);

		return localDate;
	}

	public static String getDisplayDate(LocalDate localDate) {

		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy年MM月");

		return pattern.format(localDate);
	}

}