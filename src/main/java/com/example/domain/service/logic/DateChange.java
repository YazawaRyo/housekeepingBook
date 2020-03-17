package com.example.domain.service.logic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateChange {

	/**
	 * 月末日取得<br>
	 * 渡された日付の月末日を返す
	 * 
	 * @param year  年
	 * @param month 月
	 * @return 月末日
	 */
	public static LocalDate getLastYmd(int year, int month) {

		LocalDate localDate = LocalDate.of(year, month, 1);
		localDate = localDate.plusMonths(1);
		localDate = localDate.minusDays(1);

		return localDate;
	}

	/**
	 * 日付表示変換<br>
	 * 画面表示用に日付のフォーマットを変更する
	 * 
	 * @param localDate 日付
	 * @return 日付（yyyy年MM月）
	 */
	public static String getDisplayDate(LocalDate localDate) {

		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy年MM月");

		return pattern.format(localDate);
	}

}