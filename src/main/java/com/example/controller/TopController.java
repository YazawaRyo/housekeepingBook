package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.domain.form.TopForm;
import com.example.domain.model.UserSession;

/**
 * TOP偏移用コントローラー
 * 
 * @author ryo_yazawa
 * @version 1.0
 *
 */
@Controller
public class TopController {

	private final UserSession userSession;

	public TopController(UserSession userSession) {
		this.userSession = userSession;
	}

	/**
	 * TOP画面呼び出し<br>
	 * セッションが切れている場合にはログイン画面に偏移する
	 * 
	 * @param topForm 画面入力値
	 * @param model
	 * @return TOP画面へ偏移
	 */
	@GetMapping("/top")
	public String postTop(TopForm topForm, Model model) {

		if (!userSession.isLogin()) {

			return "redirect:/";
		}

		return "top";
	}

}