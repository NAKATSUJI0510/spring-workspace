package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "items")
public class Item {
	/**
	 * フィールド
	 */
	@Id //プライマリーキー
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "category_id")
	private int categoryId;
	private String name;
	private int price;

	public int getId() {
		return id;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

}
