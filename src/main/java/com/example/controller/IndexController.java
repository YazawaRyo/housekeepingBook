package com.example.controller;

import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Infrastructure.LoginEntity;
import com.example.domain.form.LoginForm;
import com.example.domain.model.UserSession;
import com.example.domain.service.LoginService;

/**
 * ログイン用コントローラー
 * 
 * @author ryo_yazawa
 * @version 1.0
 *
 */
@Controller
public class IndexController {

	private final UserSession userSession;
	private final LoginService loginService;

	public IndexController(UserSession userSession, LoginService loginService) {
		this.userSession = userSession;
		this.loginService = loginService;
	}

	/**
	 * TOP画面偏移<br>
	 * セッションがある場合にはメニュー画面へ <br>
	 * そうでない場合にはログイン画面を表示
	 * 
	 * @return ログイン画面へ偏移
	 */
	@GetMapping("/")
	public String login() {

		// セッションチェック
		if (userSession.isLogin()) {

			return "redirect:/top";
		}

		return "login";
	}

	/**
	 * ログイン<br>
	 * 入力されたユーザー情報がDBに登録されている場合<br>
	 * TOP画面へ偏移する<br>
	 * 認証できない場合エラーを表示する
	 * 
	 * @param model
	 * @param loginForm
	 * @return
	 */
	@PostMapping("/login")
	public String postLogin(Model model, LoginForm loginForm) {

		// 入力された値でユーザーを検索する
		LoginEntity entity = loginService.selectUser(loginForm.getId(), loginForm.getPassword());

		// ユーザーが存在した場合
		if (Objects.nonNull(entity)) {
			// セッションにユーザー情報を格納してTOPへ偏移する
			userSession.setName(entity.getName());
			userSession.setAuthority(entity.getAuthority());
			return "redirect:/top";
		}

		// 表示用エラーメッセージを格納してログイン画面に戻る
		model.addAttribute("message", "ユーザー名かパスワードが間違っています。");

		return "login";
	}

	/**
	 * ログアウト<br>
	 * セッションをクリアする
	 * 
	 * @param session セッション
	 * @return ログイン画面へ偏移
	 */
	@PostMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

}