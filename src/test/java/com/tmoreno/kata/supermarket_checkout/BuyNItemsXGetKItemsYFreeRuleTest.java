package com.tmoreno.kata.supermarket_checkout;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.tmoreno.kata.supermarket_checkout.rules.BuyNItemsXGetKItemsYFreeRule;
import com.tmoreno.kata.supermarket_checkout.rules.Rule;

public class BuyNItemsXGetKItemsYFreeRuleTest {

	private static final double PRICE_DELTA = 0L;
	private static final double PRICE = 10L;
	private static final double FREE = 0L;

	private List<Item> items;
	private List<Rule> rules;
	private CheckOut checkOut;
	private Receipt receipt;

	@Before
	public void setUp() {
		items = new ArrayList<>();
		rules = new ArrayList<>();
	}

	@Test
	public void buy1ItemOfProduct1Get1ItemFreeProduct2() {
		items.add(new Item(1, PRICE));
		items.add(new Item(2, PRICE + 1));
		rules.add(new BuyNItemsXGetKItemsYFreeRule(1, 1, 1, 2));
		checkOut = new CheckOut(rules);

		receipt = checkOut.calculatePrice(items);

		assertEquals(PRICE, receipt.getItem(0).getPrice(), PRICE_DELTA);
		assertEquals(FREE, receipt.getItem(1).getPrice(), PRICE_DELTA);

		assertEquals(PRICE, receipt.getTotal(), PRICE_DELTA);
	}

	@Test
	public void buy2ItemsOfProduct1Get1ItemFreeProduct2() {
		items.add(new Item(1, PRICE));
		items.add(new Item(1, PRICE));
		items.add(new Item(2, PRICE + 1));
		rules.add(new BuyNItemsXGetKItemsYFreeRule(1, 1, 1, 2));
		checkOut = new CheckOut(rules);

		receipt = checkOut.calculatePrice(items);

		assertEquals(PRICE, receipt.getItem(0).getPrice(), PRICE_DELTA);
		assertEquals(PRICE, receipt.getItem(1).getPrice(), PRICE_DELTA);
		assertEquals(FREE, receipt.getItem(2).getPrice(), PRICE_DELTA);

		assertEquals(PRICE * 2, receipt.getTotal(), PRICE_DELTA);
	}

	@Test
	public void buy1ItemOfProduct1Get1ItemFreeProduct2With2Products2() {
		items.add(new Item(1, PRICE));
		items.add(new Item(2, PRICE + 1));
		items.add(new Item(2, PRICE + 1));
		rules.add(new BuyNItemsXGetKItemsYFreeRule(1, 1, 1, 2));
		checkOut = new CheckOut(rules);

		receipt = checkOut.calculatePrice(items);

		assertEquals(PRICE, receipt.getItem(0).getPrice(), PRICE_DELTA);
		assertEquals(FREE, receipt.getItem(1).getPrice(), PRICE_DELTA);
		assertEquals(PRICE + 1, receipt.getItem(2).getPrice(), PRICE_DELTA);

		assertEquals(PRICE * 2 + 1, receipt.getTotal(), PRICE_DELTA);
	}

	@Test
	public void buyFirstItemsFreeThenOthers() {
		items.add(new Item(2, PRICE + 1));
		items.add(new Item(2, PRICE + 1));
		items.add(new Item(1, PRICE));
		rules.add(new BuyNItemsXGetKItemsYFreeRule(1, 1, 1, 2));
		checkOut = new CheckOut(rules);

		receipt = checkOut.calculatePrice(items);

		assertEquals(FREE, receipt.getItem(0).getPrice(), PRICE_DELTA);
		assertEquals(PRICE + 1, receipt.getItem(1).getPrice(), PRICE_DELTA);
		assertEquals(PRICE, receipt.getItem(2).getPrice(), PRICE_DELTA);

		assertEquals(PRICE * 2 + 1, receipt.getTotal(), PRICE_DELTA);
	}
}
