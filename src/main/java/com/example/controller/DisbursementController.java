package com.example.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

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

	private Map<String, Integer> initauthorityOption() {
		Map<String, Integer> category = new LinkedHashMap<>();
		category.put("食費", 0);
		category.put("消耗品費", 1);
		category.put("雑費", 2);
		category.put("水道光熱費", 3);
		category.put("家賃", 4);

		return category;
	}

	@GetMapping("/disbursement_registration")
	public String getDisReg(Model model, @ModelAttribute DisbursementForm form) {

		if (!userSession.isLogin()) {

			return "redirect:/";
		}

		List<UserEntity> list = disbursementRegistrationService.selectAllUser();
		model.addAttribute("user", list);
		categoryOption = initauthorityOption();
		model.addAttribute("categoryOption", categoryOption);

		return "disbursement_registration";
	}

	@PostMapping("/disbursement_registration")
	public String postDisReg(@ModelAttribute @Validated(GroupOrder.class) DisbursementForm form,
			BindingResult bindingResult, Model model, HttpServletRequest request) {

		if (!userSession.isLogin()) {

			return "redirect:/";
		}

		if (bindingResult.hasErrors() || Objects.isNull(form.getDate())) {

			return getDisReg(model, form);
		}

		Disbursement disbursement = new Disbursement();
		disbursement.setDate(form.getDate());
		disbursement.setCategory(form.getCategory());
		disbursement.setContent(form.getContent());
		disbursement.setSize(form.getSize());
		disbursement.setBilling(form.getBilling());
		disbursement.setPayment(form.getPayment());
		disbursement.setMoney(form.getMoney());

		disbursementRegistrationService.insertDisbursement(disbursement);

		return "top";
	}

}