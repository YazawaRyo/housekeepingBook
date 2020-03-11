package com.example.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Infrastructure.UserEntity;
import com.example.domain.exception.GroupOrder;
import com.example.domain.form.DisbursementForm;
import com.example.domain.model.Disbursement;
import com.example.domain.model.UserSession;
import com.example.domain.service.DisbursementRegistrationService;

/**
 * 支出登録用コントローラー
 * 
 * @author ryo_yazawa
 * @version 1.0
 *
 */
@Controller
public class DisbursementController {

	private final UserSession userSession;
	private final DisbursementRegistrationService disbursementRegistrationService;

	public DisbursementController(UserSession userSession,
			DisbursementRegistrationService disbursementRegistrationService) {
		this.userSession = userSession;
		this.disbursementRegistrationService = disbursementRegistrationService;
	}

	private Map<String, Integer> categoryOption;

	/**
	 * selectリストアイテム作成 （分類用）
	 * 
	 * @return 分類Map(表示名,登録値)
	 */
	private Map<String, Integer> initauthorityOption() {
		Map<String, Integer> category = new LinkedHashMap<>();
		category.put("食費", 0);
		category.put("消耗品費", 1);
		category.put("雑費", 2);
		category.put("水道光熱費", 3);
		category.put("家賃", 4);

		return category;
	}

	/**
	 * 支出登録画面呼び出し
	 * 
	 * @param form  画面入力値
	 * @param model
	 * @return 支出登録画面へ偏移
	 */
	@GetMapping("/disbursement_registration")
	public String getDisReg(@ModelAttribute DisbursementForm form, Model model) {

		// sessionチェック
		if (!userSession.isLogin()) {

			return "redirect:/";
		}

		// 支払い、請求先用にユーザーを取得
		List<UserEntity> list = disbursementRegistrationService.selectAllUser();
		model.addAttribute("user", list);
		// 分類用Mapを生成
		categoryOption = initauthorityOption();
		model.addAttribute("categoryOption", categoryOption);

		return "disbursement_registration";
	}

	/**
	 * 支出入力値登録
	 * 
	 * @param form          画面入力値
	 * @param bindingResult 入力チェック結果
	 * @param model
	 * @return TOP画面へ偏移
	 */
	@PostMapping("/disbursement_registration")
	public String postDisReg(@ModelAttribute @Validated(GroupOrder.class) DisbursementForm form,
			BindingResult bindingResult, Model model) {

		// sessionチェック
		if (!userSession.isLogin()) {

			return "redirect:/";
		}

		// エラーチェック
		if (bindingResult.hasErrors()) {

			return getDisReg(form, model);
		}

		// 登録用に値の詰め替え
		Disbursement disbursement = new Disbursement();
		disbursement.setDate(form.getDate());
		disbursement.setCategory(form.getCategory());
		disbursement.setContent(form.getContent());
		disbursement.setSize(form.getSize());
		disbursement.setBilling(form.getBilling());
		disbursement.setPayment(form.getPayment());
		disbursement.setMoney(form.getMoney());

		disbursementRegistrationService.insertDisbursement(disbursement);

		return "redirect:/top";
	}

}