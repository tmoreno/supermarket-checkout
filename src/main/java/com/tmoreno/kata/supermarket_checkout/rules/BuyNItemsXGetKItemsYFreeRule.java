package com.tmoreno.kata.supermarket_checkout.rules;

import java.util.ArrayList;
import java.util.List;

import com.tmoreno.kata.supermarket_checkout.Item;

public class BuyNItemsXGetKItemsYFreeRule implements Rule {

	private int numItems;
	private int productId;
	private int numItemsFree;
	private int productIdFree;

	private List<List<Item>> items;
	private List<List<Item>> itemsForFree;

	public BuyNItemsXGetKItemsYFreeRule(int numItems, int productId,
			int numItemsFree, int productIdFree) {

		this.numItems = numItems;
		this.productId = productId;
		this.numItemsFree = numItemsFree;
		this.productIdFree = productIdFree;

		items = new ArrayList<>();
		items.add(new ArrayList<Item>());

		itemsForFree = new ArrayList<>();
		itemsForFree.add(new ArrayList<Item>());
	}

	@Override
	public void apply(Item item) {
		if (item.getId() == productId) {
			List<Item> group = items.get(items.size() - 1);

			if (group.size() < numItems) {
				group.add(item);

				if (group.size() == numItems && !itemsForFree.isEmpty()) {
					for (Item itemFree : itemsForFree.get(0)) {
						itemFree.setPrice(0L);
					}

					if (itemsForFree.get(0).size() == numItemsFree) {
						itemsForFree.remove(0);
					}
				}
			}
			else if (group.size() == numItems) {
				group = new ArrayList<>();
				group.add(item);
				items.add(group);
			}
		}
		else if (item.getId() == productIdFree) {
			List<Item> group = itemsForFree.get(itemsForFree.size() - 1);

			if (group.size() < numItemsFree) {
				group.add(item);

				if (group.size() == numItemsFree
						&& items.get(items.size() - 1).size() == numItems) {
					item.setPrice(0L);
				}
			}
			else if (group.size() == numItemsFree) {
				group = new ArrayList<>();
				group.add(item);
				itemsForFree.add(group);
			}
		}
	}

}
