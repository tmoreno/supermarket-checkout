package com.tmoreno.kata.supermarket_checkout;

import java.util.ArrayList;
import java.util.List;

public class Receipt {

	private List<Item> items;

	public Receipt() {
		items = new ArrayList<>();
	}

	public void addItem(Item item) {
		items.add(item);
	}

	public Item getItem(int i) {
		return items.get(i);
	}

	public double getTotal() {
		double total = 0L;

		for (Item item : items) {
			total += item.getPrice();
		}

		return total;
	}
}
