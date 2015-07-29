package com.tmoreno.kata.supermarket_checkout.specialprice;

import com.tmoreno.kata.supermarket_checkout.Item;

public class HalfPrice implements SpecialPrice {

	@Override
	public void applySpecialPrice(Item item) {
		item.setPrice(item.getPrice() / 2);
	}

}
