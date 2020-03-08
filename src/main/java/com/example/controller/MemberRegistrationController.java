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

@Controller
public class MemberRegistrationController {

	private final MemberRegistrationService memberRegistrationService;

	public MemberRegistrationController(MemberRegistrationService memberRegistrationService) {
		this.memberRegistrationService = memberRegistrationService;
	}

	private Map<String, Integer> authorityOption;

	private Map<String, Integer> initAuthorityOption() {

		Map<String, Integer> authority = new LinkedHashMap<>();
		authority.put("管理", 0);
		authority.put("通常", 1);

		return authority;
	}

	@GetMapping("/member_registration")
	public String getMemReg(@ModelAttribute MemberForm form, Model model) {

		authorityOption = initAuthorityOption();
		model.addAttribute("authorityOption", authorityOption);

		return "member_registration";
	}

	@PostMapping("/member_registration")
	public String postMemReg(@ModelAttribute @Validated(GroupOrder.class) MemberForm form, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			return getMemReg(form, model);
		}

		Member member = new Member();
		member.setId(form.getId());
		member.setPassword(form.getPassword());
		member.setName(form.getName());
		member.setAuthority(form.getAuthority());

		memberRegistrationService.insertMember(member);

		return "redirect:/";
	}

}