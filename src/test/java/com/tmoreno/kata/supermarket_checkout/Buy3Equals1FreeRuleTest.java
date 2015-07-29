package com.tmoreno.kata.supermarket_checkout;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.tmoreno.kata.supermarket_checkout.rules.Buy3Equals1FreeRule;
import com.tmoreno.kata.supermarket_checkout.rules.Rule;

public class Buy3Equals1FreeRuleTest {

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
	public void buy1Item() {
		items.add(new Item(1, PRICE));
		rules.add(new Buy3Equals1FreeRule());
		checkOut = new CheckOut(rules);

		receipt = checkOut.calculatePrice(items);

		assertEquals(PRICE, receipt.getItem(0).getPrice(), PRICE_DELTA);
		assertEquals(PRICE, receipt.getTotal(), PRICE_DELTA);
	}

	@Test
	public void buy2DifferentItems() {
		items.add(new Item(1, PRICE));
		items.add(new Item(2, PRICE));
		rules.add(new Buy3Equals1FreeRule());
		checkOut = new CheckOut(rules);

		receipt = checkOut.calculatePrice(items);

		assertEquals(PRICE, receipt.getItem(0).getPrice(), PRICE_DELTA);
		assertEquals(PRICE, receipt.getItem(1).getPrice(), PRICE_DELTA);

		assertEquals(PRICE * 2, receipt.getTotal(), PRICE_DELTA);
	}

	@Test
	public void buy2Equals1DifferentItems() {
		items.add(new Item(1, PRICE));
		items.add(new Item(1, PRICE));
		items.add(new Item(2, PRICE));
		rules.add(new Buy3Equals1FreeRule());
		checkOut = new CheckOut(rules);

		receipt = checkOut.calculatePrice(items);

		assertEquals(PRICE, receipt.getItem(0).getPrice(), PRICE_DELTA);
		assertEquals(PRICE, receipt.getItem(1).getPrice(), PRICE_DELTA);
		assertEquals(PRICE, receipt.getItem(2).getPrice(), PRICE_DELTA);

		assertEquals(PRICE * 3, receipt.getTotal(), PRICE_DELTA);
	}

	@Test
	public void buy3EqualsItems() {
		items.add(new Item(1, PRICE));
		items.add(new Item(1, PRICE));
		items.add(new Item(1, PRICE));
		rules.add(new Buy3Equals1FreeRule());
		checkOut = new CheckOut(rules);

		receipt = checkOut.calculatePrice(items);

		assertEquals(PRICE, receipt.getItem(0).getPrice(), PRICE_DELTA);
		assertEquals(PRICE, receipt.getItem(1).getPrice(), PRICE_DELTA);
		assertEquals(FREE, receipt.getItem(2).getPrice(), PRICE_DELTA);

		assertEquals(PRICE * 2, receipt.getTotal(), PRICE_DELTA);
	}
}
