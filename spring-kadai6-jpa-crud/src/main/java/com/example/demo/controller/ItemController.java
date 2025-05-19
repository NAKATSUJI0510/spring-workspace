package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Item;
import com.example.demo.repository.ItemRepository;

@Controller
public class ItemController {
	@Autowired
	ItemRepository itemRepository;

	@GetMapping("/items/{id}/delete")
	public String delete(
			@PathVariable("id") Integer id) {
		itemRepository.deleteById(id);
		return "redirect:/items";
	}

	@PostMapping("/items/{id}/edit")
	public String update(
			@PathVariable("id") Integer id,
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "price", defaultValue = "") Integer price) {

		Item item = itemRepository.findById(id).get();
		item.setCategoryId(categoryId);
		item.setName(name);
		item.setPrice(price);

		itemRepository.save(item);
		return "redirect:/items";
	}

	@GetMapping("/items/{id}/edit")
	public String edit(
			@PathVariable("id") Integer id, Model model) {
		Item item = itemRepository.findById(id).get();
		model.addAttribute("item", item);
		return "editItem";
	}

	@PostMapping("/items/add")
	public String store(
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "price", defaultValue = "") Integer price) {

		Item item = new Item(categoryId, name, price);
		itemRepository.save(item);
		return "redirect:/items";
	}

	@GetMapping("/items/add")
	public String create() {
		return "addItem";
	}

	@GetMapping("/items")
	public String index(Model model) {
		List<Item> list = itemRepository.findAll();
		model.addAttribute("items", list);
		return "items";
	}

}
