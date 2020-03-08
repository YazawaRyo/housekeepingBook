package com.example.domain.service.logic;

import java.util.List;

import com.example.Infrastructure.ViewEntity;
import com.example.domain.model.MoneyBean;

public class ManeySum {

	public static MoneyBean setCategoryMoney(List<ViewEntity> viewEntitylist) {

		int foodCost = 0;
		int suppliesExpense = 0;
		int miscellaneousExpenses = 0;
		int utilitiesExpense = 0;
		int rent = 0;

		for (ViewEntity viewEntity : viewEntitylist) {

			String category = viewEntity.getCategory();

			switch (category) {
			case "0":
				foodCost += viewEntity.getMoney();
				break;
			case "1":
				suppliesExpense += viewEntity.getMoney();
				break;
			case "2":
				miscellaneousExpenses += viewEntity.getMoney();
				break;
			case "3":
				utilitiesExpense += viewEntity.getMoney();
				break;
			case "4":
				rent += viewEntity.getMoney();
				break;
			}
		}

		MoneyBean moneyForm = new MoneyBean();
		moneyForm.setFood_cost(foodCost);
		moneyForm.setSupplies_expense(suppliesExpense);
		moneyForm.setMiscellaneous_expenses(miscellaneousExpenses);
		moneyForm.setUtilities_expense(utilitiesExpense);
		moneyForm.setRent(rent);

		return moneyForm;
	}

	public static int setUserMoney(List<ViewEntity> viewEntitylist, String sessionName, int i) {

		int total = 0;

		for (ViewEntity viewEntity : viewEntitylist) {

			String billing = viewEntity.getBilling();
			String payment = viewEntity.getPayment();
			viewEntity.setPerson(viewEntity.getMoney() / i);

			if (billing.equals("全員")) {

				total += (viewEntity.getPerson());

			} else if (billing.equals(sessionName)) {

				total += viewEntity.getMoney();

			}

			if (payment.equals(sessionName)) {
				total -= viewEntity.getMoney();
			}
		}

		return total;
	}
}