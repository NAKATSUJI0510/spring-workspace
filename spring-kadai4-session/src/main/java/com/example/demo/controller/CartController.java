package com.example.demo.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Account;
import com.example.demo.model.Cart;
import com.example.demo.model.Item;

@Controller
public class CartController {

	@Autowired
	HttpSession session;

	@Autowired
	Cart cart;

	@Autowired
	Account account;

	@GetMapping({ "/cart/login", "/cart/logout" })
	public String index() {
		session.invalidate();
		return "cartLogin";
	}

	@PostMapping("/cart/login")
	public String login(
			@RequestParam("name") String name) {
		account.setName(name);
		return "cart";
	}

	@GetMapping("/cart/clear")
	public String clearCart() {
		List<Item> items = cart.getItems();
		items.clear();
		return "cart";
	}

	@PostMapping("/cart/add")
	public String addCart(
			@RequestParam("name") String name,
			@RequestParam("price") Integer price,
			Model model) {
		// セッションからカート内リストを取得
		List<Item> items = cart.getItems();
		// 追加する商品をインスタンス化
		Item item = new Item(name, price);
		// カート内商品リストに商品のインスタンスを追加
		items.add(item);

		//		if (!name.isEmpty()) {
		//			model.addAttribute("item", item);
		//		}
		//		if (price > 0) {
		//			model.addAttribute("item", item);
		//		}
		return "cart";
	}

	@GetMapping("/cart")
	public String showCart() {
		return "cart";
	}
}
