package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Category;
import com.example.demo.entity.Item;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ItemRepository;

@Controller
public class ItemController {

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@GetMapping("/items")
	public String items(
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(name = "price", defaultValue = "") Integer price,
			Model model) {

		List<Category> categoryList = categoryRepository.findAll();
		model.addAttribute("categories", categoryList);

		List<Item> itemList = null;
		if (categoryId == null) {
			itemList = itemRepository.findAll();
		} else {
			itemList = itemRepository.findByCategoryId(categoryId);
		}

		if (price == null) {
			itemList = itemRepository.findAll();
		} else {
			itemList = itemRepository.findByPrice(price);
		}
		model.addAttribute("items", itemList);
		return "items";
	}
}

//	@GetMapping("/items")
//	public String index(Model model) {
//		List<Item> itemList = itemRepository.findAll();
//		model.addAttribute("items", itemList);
//
//		return "items";
//	}
