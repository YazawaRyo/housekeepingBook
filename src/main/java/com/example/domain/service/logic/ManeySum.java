package com.example.domain.service.logic;

import java.util.List;

import com.example.Infrastructure.ViewEntity;
import com.example.domain.model.MoneyBean;

public class ManeySum {

	/**
	 * カテゴリ別合計の算出<br>
	 * カテゴリごとに金額を合計する
	 * 
	 * @param viewEntitylist
	 * @return カテゴリ別の合計が格納されたBean
	 */
	public static MoneyBean setCategoryMoney(List<ViewEntity> viewEntitylist) {

		int foodCost = 0;
		int suppliesExpense = 0;
		int miscellaneousExpenses = 0;
		int utilitiesExpense = 0;
		int rent = 0;

		// カテゴリごとに合計する
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

		// 受け渡し用に値を格納する
		MoneyBean moneyForm = new MoneyBean();
		moneyForm.setFood_cost(foodCost);
		moneyForm.setSupplies_expense(suppliesExpense);
		moneyForm.setMiscellaneous_expenses(miscellaneousExpenses);
		moneyForm.setUtilities_expense(utilitiesExpense);
		moneyForm.setRent(rent);

		return moneyForm;
	}

	/**
	 * 個人合計算出<br>
	 * ログインしているユーザーの合計金額の算出
	 * 
	 * @param viewEntitylist 当月支出データ
	 * @param sessionName    ログインしているユーザー名
	 * @param i              ユーザー人数
	 * @return 個人合計金額
	 */
	public static int setUserMoney(List<ViewEntity> viewEntitylist, String sessionName, int i) {

		int total = 0;

		for (ViewEntity viewEntity : viewEntitylist) {

			String billing = viewEntity.getBilling();
			String payment = viewEntity.getPayment();

			// 一人当たりの金額を算出して格納する
			viewEntity.setPerson(viewEntity.getMoney() / i);

			// 請求者が全員の場合
			if (billing.equals("全員")) {

				// 一人当たりの金額を加算する
				total += (viewEntity.getPerson());

				// 請求者がログインユーザーの場合
			} else if (billing.equals(sessionName)) {

				// 全額を加算する
				total += viewEntity.getMoney();

			}

			// 支払いがログインユーザーの場合
			if (payment.equals(sessionName)) {
				// 合計金額から支払った全額を減算する
				total -= viewEntity.getMoney();
			}
		}

		return total;
	}
}