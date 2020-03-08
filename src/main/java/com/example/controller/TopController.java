package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.domain.form.TopForm;
import com.example.domain.model.UserSession;

@Controller
public class TopController {

	private final UserSession userSession;

	public TopController(UserSession userSession) {
		this.userSession = userSession;
	}

	@GetMapping("/top")
	public String postTop(TopForm topForm, Model model) {

		if (!userSession.isLogin()) {

			return "redirect:/";
		}

		return "top";
	}

}