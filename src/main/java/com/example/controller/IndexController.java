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

@Controller
public class IndexController {

	private final UserSession userSession;
	private final LoginService loginService;

	public IndexController(UserSession userSession, LoginService loginService) {
		this.userSession = userSession;
		this.loginService = loginService;
	}

	@GetMapping("/")
	public String login() {

		if (userSession.isLogin()) {

			return "redirect:/top";
		}

		return "login";
	}

	@PostMapping("/login")
	public String postLogin(Model model, LoginForm loginForm) {

		LoginEntity entity = loginService.selectUser(loginForm.getId(), loginForm.getPassword());

		if (Objects.nonNull(entity)) {
			userSession.setName(entity.getName());
			userSession.setAuthority(entity.getAuthority());
			return "redirect:/top";
		}

		model.addAttribute("message", "ユーザー名かパスワードが間違っています。");

		return "login";
	}

	@PostMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

}