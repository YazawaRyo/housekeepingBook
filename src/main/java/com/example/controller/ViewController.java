package com.example.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Infrastructure.UserEntity;
import com.example.Infrastructure.ViewEntity;
import com.example.domain.form.TopForm;
import com.example.domain.model.MoneyBean;
import com.example.domain.model.UserSession;
import com.example.domain.service.DisbursementRegistrationService;
import com.example.domain.service.DropService;
import com.example.domain.service.ViewService;
import com.example.domain.service.logic.CategoryChange;
import com.example.domain.service.logic.DateChange;
import com.example.domain.service.logic.ManeySum;

/**
 * 支出閲覧用コントローラー
 * 
 * @author ryo_yazawa
 * @version 1.0
 *
 */
@Controller
public class ViewController {

	private final UserSession userSession;
	private final ViewService viewService;
	private final DropService dropService;
	private final DisbursementRegistrationService disbursementRegistrationService;

	public ViewController(UserSession userSession, ViewService viewService, DropService dropService,
			DisbursementRegistrationService disbursementRegistrationService) {
		this.userSession = userSession;
		this.viewService = viewService;
		this.dropService = dropService;
		this.disbursementRegistrationService = disbursementRegistrationService;
	}

	/**
	 * 指定月の支出呼び出し<br>
	 * DBから指定の月の支出データを表示する<br>
	 * ログインユーザーの合計とカテゴリ別の支出を算出する
	 * 
	 * @param model
	 * @param topForm       画面入力値
	 * @param bindingResult 入力チェック結果
	 * @return 閲覧画面へ偏移
	 */
	@GetMapping("/view")
	public String getView(Model model, @Validated TopForm topForm, BindingResult bindingResult) {

		// sessionチェック
		if (!userSession.isLogin()) {

			return "redirect:/";
		}

		// エラーチェック
		if (bindingResult.hasErrors()) {

			return "top";
		}

		// 検索用に月初と月末日を取得
		LocalDate from = LocalDate.of(topForm.getYear(), topForm.getMonth(), 1);
		LocalDate to = DateChange.getLastYmd(topForm.getYear(), topForm.getMonth());

		// 表示する月のデータを取得
		List<ViewEntity> viewEntitylist = viewService.selectALLData(from, to);

		// 一人当たりの金額算出の為全ユーザーを取得する
		List<UserEntity> userList = disbursementRegistrationService.selectAllUser();
		int i = userList.size();

		// ログインユーザーの合計金額の算出
		int total = ManeySum.setUserMoney(viewEntitylist, userSession.getName(), i);

		// カテゴリー別合計の算出
		MoneyBean moneyBean = ManeySum.setCategoryMoney(viewEntitylist);

		// 表示用にカテゴリ名を変換
		for (ViewEntity viewEntity : viewEntitylist) {
			String category = viewEntity.getCategory();
			viewEntity.setCategory(CategoryChange.setCategory(category));
		}

		model.addAttribute("year", topForm.getYear());
		model.addAttribute("month", topForm.getMonth());
		model.addAttribute("viewEntitylist", viewEntitylist);
		model.addAttribute("moneyBean", moneyBean);
		model.addAttribute("total", total);

		return "view";
	}

	/**
	 * 前後閲覧画面表示<br>
	 * 押下されたボタンに応じて前月または翌月の閲覧画面を表示する
	 * 
	 * @param topForm 画面入力値
	 * @param button  （0 加算,1 減算）
	 * @return 前月または翌月の閲覧画面
	 */
	@GetMapping("/move")
	public String pageMove(TopForm topForm, @RequestParam String button) {

		// sessionチェック
		if (!userSession.isLogin()) {

			return "redirect:/";
		}

		// 現在表示している日付の取得
		LocalDate from = LocalDate.of(topForm.getYear(), topForm.getMonth(), 1);

		// 月加減判定
		if ("0".equals(button)) {
			// １か月加算
			from = from.minusMonths(1);
		} else {
			// １か月減算
			from = from.plusMonths(1);
		}

		return "redirect:/view?year=" + from.getYear() + "&month=" + from.getMonthValue();
	}

	/**
	 * 支出データ削除<br>
	 * 選択されたデータをDBから削除し閲覧画面へ戻る
	 * 
	 * @param topForm    画面入力値
	 * @param numberList 削除対象
	 * @return 表示中閲覧画面へ偏移
	 */
	@PostMapping("/drop")
	public String postDrop(TopForm topForm, @RequestParam List<Integer> numberList) {

		// sessionチェック
		if (!userSession.isLogin()) {

			return "redirect:/";
		}

		// 権限が管理者のみ削除を実行
		if (0 == userSession.getAuthority()) {

			dropService.dropNumber(numberList);
		}

		return "redirect:/view?year=" + topForm.getYear() + "&month=" + topForm.getMonth();
	}

}