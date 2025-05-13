package com.example.demo.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ItemController {
	@PostMapping("item/detail")
	public String comdetail(
			@RequestParam(name = "name", defaultValue = "未設定") String name,
			@RequestParam(name = "price", defaultValue = "") Integer price,
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(name = "genre", defaultValue = "") String[] genreList,
			@RequestParam(name = "releaseDate", defaultValue = "") LocalDate releaseDate,
			Model model) {
		//　共用のデータ置き場にパラメータを置く
		model.addAttribute("name", name);
		model.addAttribute("price", price);
		model.addAttribute("categoryId", categoryId);
		model.addAttribute("genreList", genreList);
		model.addAttribute("releaseDate", releaseDate);
		return "itemDetailConfirm";
	}

	@GetMapping("/item/detail")
	public String detail() {
		return "itemDetailForm";
	}

	@GetMapping("/item/{id}")
	public String show(
			@PathVariable("id") int id,
			Model model) {
		// 商品取得処理
		//		switch (id) {
		//		case 101:
		//			model.addAttribute("name", "ボールペン");
		//			model.addAttribute("price", 100);
		//			break;
		//		case 102:
		//			model.addAttribute("name", "消しゴム");
		//			model.addAttribute("price", "50");
		//			break;
		//		default:
		//			model.addAttribute("name", "未設定");
		//			model.addAttribute("price", 0);
		//			break;
		//		}
		if (id == 101) {
			model.addAttribute("name", "ボールペン");
			model.addAttribute("price", 100);
		} else if (id == 102) {
			model.addAttribute("name", "消しゴム");
			model.addAttribute("price", "50");
		} else {
			model.addAttribute("name", "未設定");
			model.addAttribute("price", 0);
		}

		return "itemConfirm";
	}

	@GetMapping("/item/hidden")
	public String hidden() {
		return "itemFormHidden";
	}

	@PostMapping("/item")
	public String confirm(
			@RequestParam(name = "name", defaultValue = "未入力") String name,
			@RequestParam(name = "price", defaultValue = "100") int price,
			Model model) {
		model.addAttribute("name", name);
		model.addAttribute("price", price);
		return "itemConfirm";
	}

	@GetMapping("/")
	public String index() {
		return "itemForm";
	}
}
