package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Category;
import com.example.demo.entity.Item;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ItemRepository;

@Controller
public class ItemController {
	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ItemRepository itemRepository;

	@PostMapping("/items/{id}/delete")
	public String delete(
			@PathVariable("id") Integer id) {
		// 商品ID（itemsテーブルの主キー）で削除
		itemRepository.deleteById(id);
		// 画面遷移
		return "redirect:/items";
	}

	@PostMapping("/items/{id}/edit")
	public String updata(
			@PathVariable("id") Integer id,
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "price", defaultValue = "") Integer price) {
		// リクエストパラメータから更新ｓる商品をインスタンス化
		Item item = new Item();
		item.setId(id);
		item.setCategoryId(categoryId);
		item.setName(name);
		item.setPrice(price);

		itemRepository.save(item);
		return "redirect:/items";
	}

	@GetMapping("/items/{id}/edit")
	public String edit(
			@PathVariable("id") Integer id,
			Model model) {
		// 更新する商品のインスタンスを取得
		Item item = itemRepository.findById(id).get();
		// データ置き場に登録
		model.addAttribute("item", item);
		// 画面遷移
		return "editItem";
	}

	@PostMapping("/items/add")
	public String store(
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "price", defaultValue = "") Integer price) {

		// 追加する商品をインスタンス化
		Item item = new Item();
		item.setCategoryId(categoryId);
		item.setName(name);
		item.setPrice(price);

		// 商品をデータベースに登録
		itemRepository.save(item);
		return "redirect:/items";
	}

	@GetMapping("/items/add")
	public String create() {
		return "addItem";
	}

	@GetMapping({ "/", "/items" })
	public String index(Model model) {
		// データベースから全てのカテゴリのカテゴリリストを取得
		List<Category> categoryList = categoryRepository.findAll();
		// データベースから全ての商品の商品リストを取得
		List<Item> list = itemRepository.findAll();
		// 取得したカテゴリリストと商品リストをデータ置き場に設定
		model.addAttribute("categories", categoryList);
		model.addAttribute("items", list);
		// 画面遷移
		return "items";// テンプレートファイルの中のitemsを表示するから
	}

}
