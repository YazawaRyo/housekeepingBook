package com.example.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.domain.exception.GroupOrder;
import com.example.domain.form.MemberForm;
import com.example.domain.model.Member;
import com.example.domain.service.MemberRegistrationService;

/**
 * ユーザー登録用コントローラー
 * 
 * @author ryo_yazawa
 * @version 1.0
 *
 */
@Controller
public class MemberRegistrationController {

	private final MemberRegistrationService memberRegistrationService;

	public MemberRegistrationController(MemberRegistrationService memberRegistrationService) {
		this.memberRegistrationService = memberRegistrationService;
	}

	private Map<String, Integer> authorityOption;

	/**
	 * ラジオボタンアイテム作成 （権限用）
	 * 
	 * @return 権限「Map(表示名,登録値)
	 */
	private Map<String, Integer> initAuthorityOption() {

		Map<String, Integer> authority = new LinkedHashMap<>();
		authority.put("管理", 0);
		authority.put("通常", 1);

		return authority;
	}

	/**
	 * ユーザー登録画面呼び出し<br>
	 * 画面表示用のアイテムを受け渡す
	 * 
	 * @param form  画面入力値
	 * @param model
	 * @return ユーザー登録画面へ偏移
	 */
	@GetMapping("/member_registration")
	public String getMemReg(@ModelAttribute MemberForm form, Model model) {

		// 権限用Mapを生成
		authorityOption = initAuthorityOption();
		model.addAttribute("authorityOption", authorityOption);

		return "member_registration";
	}

	/**
	 * ユーザー情報入力値登録<br>
	 * 入力値が正常であれば値を詰め替えDBへ登録する<br>
	 * エラーがある場合は入力画面へ戻る
	 * 
	 * @param form          画面入力値
	 * @param bindingResult 入力チェック結果
	 * @param model
	 * @return ログイン画面へ偏移
	 */
	@PostMapping("/member_registration")
	public String postMemReg(@ModelAttribute @Validated(GroupOrder.class) MemberForm form, BindingResult bindingResult,
			Model model) {

		// エラーチェック
		if (bindingResult.hasErrors()) {
			return getMemReg(form, model);
		}

		// 登録用に値の詰め替え
		Member member = new Member();
		member.setId(form.getId());
		member.setPassword(form.getPassword());
		member.setName(form.getName());
		member.setAuthority(form.getAuthority());

		memberRegistrationService.insertMember(member);

		return "redirect:/";
	}

}