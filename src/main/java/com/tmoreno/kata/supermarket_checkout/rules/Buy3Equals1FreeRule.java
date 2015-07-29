package com.tmoreno.kata.supermarket_checkout.rules;

import java.util.HashMap;
import java.util.Map;

import com.tmoreno.kata.supermarket_checkout.Item;

public class Buy3Equals1FreeRule implements Rule {

	private Map<Integer, Integer> groupedItems;

	public Buy3Equals1FreeRule() {
		groupedItems = new HashMap<>();
	}

	@Override
	public void apply(Item item) {
		if (groupedItems.get(item.getId()) == null) {
			groupedItems.put(item.getId(), 0);
		}

		Integer numGroupedItems = groupedItems.get(item.getId()) + 1;

		if (numGroupedItems % 3 == 0) {
			item.setPrice(0L);
		}

		groupedItems.put(item.getId(), numGroupedItems);
	}

}
