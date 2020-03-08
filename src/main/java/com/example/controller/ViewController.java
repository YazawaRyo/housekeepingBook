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

	@GetMapping("/view")
	public String getView(Model model, @Validated TopForm topForm, BindingResult bindingResult) {

		if (!userSession.isLogin()) {

			return "redirect:/";
		}

		if (bindingResult.hasErrors()) {

			return "top";
		}

		LocalDate from = LocalDate.of(topForm.getYear(), topForm.getMonth(), 1);

		LocalDate to = DateChange.getLastYmd(topForm.getYear(), topForm.getMonth());

		List<ViewEntity> viewEntitylist = viewService.selectALLData(from, to);

		List<UserEntity> userList = disbursementRegistrationService.selectAllUser();
		int i = userList.size();

		int total = ManeySum.setUserMoney(viewEntitylist, userSession.getName(), i);

		MoneyBean moneyBean = ManeySum.setCategoryMoney(viewEntitylist);

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

	@GetMapping("/move")
	public String pageMove(TopForm topForm, @RequestParam String button) {

		if (!userSession.isLogin()) {

			return "redirect:/";
		}

		LocalDate from = LocalDate.of(topForm.getYear(), topForm.getMonth(), 1);

		if ("0".equals(button)) {
			from = from.minusMonths(1);
		} else {
			from = from.plusMonths(1);
		}

		return "redirect:/view?year=" + from.getYear() + "&month=" + from.getMonthValue();
	}

	@PostMapping("/drop")
	public String postDrop(TopForm topForm, @RequestParam List<Integer> numberList) {

		if (!userSession.isLogin()) {

			return "redirect:/";
		}

		if (0 == userSession.getAuthority()) {

			dropService.dropNumber(numberList);
		}

		return "redirect:/view?year=" + topForm.getYear() + "&month=" + topForm.getMonth();
	}

}