package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	private static final String USER_ID = "student";
	private static final String USER_PASSWORD = "himitu";

	@PostMapping("/login")
	public String login(@RequestParam("userId") String userId,
			@RequestParam("password") String password,
			Model model) {

		String nextPage = "";
		if (USER_ID.equals(userId) && USER_PASSWORD.equals(password)) {
			nextPage = "index";
		} else {
			String errorMessage = "";
			if (!USER_ID.equals(userId)) {
				errorMessage = "ユーザーIDが一致しませんでした";
			} else {
				errorMessage = "パスワードが一致しませんでした";
			}
			model.addAttribute("message", errorMessage);
			nextPage = "login";

		}

		return nextPage;
	}

	@GetMapping("/login")
	public String index() {
		return "login";
	}

}
