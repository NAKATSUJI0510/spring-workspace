package com.example.demo.quiz;

import java.util.Scanner;

public class Quiz3 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("お金を入れてください：");
		int money = scan.nextInt();

		if (money < 100) {
			System.out.println("お金が足りません");
		} else if (money >= 100) {
			System.out.println("購入できました。" + "おつりは" + (money - 120) + "円です！");
		}
	}
}
