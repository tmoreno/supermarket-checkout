package com.tmoreno.kata.supermarket_checkout.rules;

import com.tmoreno.kata.supermarket_checkout.Item;

public interface Rule {

	void apply(Item item);
}
