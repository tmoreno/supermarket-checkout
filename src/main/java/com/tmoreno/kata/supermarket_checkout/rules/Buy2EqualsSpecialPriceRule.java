package com.tmoreno.kata.supermarket_checkout.rules;

import java.util.HashMap;
import java.util.Map;

import com.tmoreno.kata.supermarket_checkout.Item;
import com.tmoreno.kata.supermarket_checkout.specialprice.SpecialPrice;

public class Buy2EqualsSpecialPriceRule implements Rule {

	private SpecialPrice specialPrice;
	private Map<Integer, Integer> groupedItems;

	public Buy2EqualsSpecialPriceRule(SpecialPrice specialPrice) {
		this.specialPrice = specialPrice;

		groupedItems = new HashMap<>();
	}

	@Override
	public void apply(Item item) {
		if (groupedItems.get(item.getId()) == null) {
			groupedItems.put(item.getId(), 0);
		}

		Integer numGroupedItems = groupedItems.get(item.getId()) + 1;

		if (numGroupedItems % 2 == 0) {
			specialPrice.applySpecialPrice(item);
		}

		groupedItems.put(item.getId(), numGroupedItems);
	}

}
