package com.tmoreno.kata.supermarket_checkout;

import java.util.List;

import com.tmoreno.kata.supermarket_checkout.rules.Rule;

public class CheckOut {

	private List<Rule> rules;

	public CheckOut(List<Rule> rules) {
		this.rules = rules;
	}

	public Receipt calculatePrice(List<Item> items) {
		Receipt receipt = new Receipt();

		for (Item item : items) {

			for (Rule rule : rules) {
				rule.apply(item);
			}

			receipt.addItem(item);
		}

		return receipt;
	}

}
