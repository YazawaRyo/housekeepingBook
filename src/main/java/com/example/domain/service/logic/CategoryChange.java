package com.example.domain.service.logic;

public class CategoryChange {

	public static String setCategory(String category) {

		switch (category) {
		case "0":
			category = "食費";
			break;
		case "1":
			category = "消耗品費";
			break;
		case "2":
			category = "雑費";
			break;
		case "3":
			category = "水道光熱費";
			break;
		case "4":
			category = "家賃";
			break;
		}

		return category;
	}

}