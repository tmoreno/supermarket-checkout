package com.tmoreno.kata.supermarket_checkout.rules;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tmoreno.kata.supermarket_checkout.Item;

public class Buy3ItemsSetCheapestFreeRule implements Rule {

	private Set<Integer> itemsOnSet;
	private List<Set<Item>> groupedItems;

	public Buy3ItemsSetCheapestFreeRule(Set<Integer> itemsOnSet) {
		this.itemsOnSet = itemsOnSet;

		groupedItems = new ArrayList<>();
	}

	@Override
	public void apply(Item item) {
		if (itemsOnSet.contains(item.getId())) {

			if (groupedItems.isEmpty()) {
				createNewGroup(item);
				return;
			}

			boolean isGrouped = false;
			for (Set<Item> group : groupedItems) {
				if (!group.contains(item)) {
					group.add(item);
					isGrouped = true;

					if (group.size() == 3) {
						cheapestItemIsFree(group);
					}

					break;
				}
			}

			if (!isGrouped) {
				createNewGroup(item);
			}
		}
	}

	private void cheapestItemIsFree(Set<Item> group) {
		Item cheapestItem = null;

		for (Item itemGrouped : group) {
			if (cheapestItem == null
					|| (cheapestItem.getPrice() > itemGrouped.getPrice())) {
				cheapestItem = itemGrouped;
			}
		}

		cheapestItem.setPrice(0L);
	}

	private void createNewGroup(Item item) {
		Set<Item> newGroup = new HashSet<>();
		newGroup.add(item);

		groupedItems.add(newGroup);
	}

}
