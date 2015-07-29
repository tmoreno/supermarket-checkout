package com.tmoreno.kata.supermarket_checkout;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.tmoreno.kata.supermarket_checkout.rules.Buy2EqualsSpecialPriceRule;
import com.tmoreno.kata.supermarket_checkout.rules.Rule;
import com.tmoreno.kata.supermarket_checkout.specialprice.HalfPrice;
import com.tmoreno.kata.supermarket_checkout.specialprice.SpecialPrice;

public class Buy2EqualsSpecialPriceRuleTest {

	private static final double PRICE_DELTA = 0L;
	private static final double PRICE = 10L;

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
		SpecialPrice specialPrice = mock(SpecialPrice.class);
		rules.add(new Buy2EqualsSpecialPriceRule(specialPrice));
		checkOut = new CheckOut(rules);

		receipt = checkOut.calculatePrice(items);

		assertEquals(PRICE, receipt.getItem(0).getPrice(), PRICE_DELTA);
		assertEquals(PRICE, receipt.getTotal(), PRICE_DELTA);
	}

	@Test
	public void buy2DifferentItems() {
		items.add(new Item(1, PRICE));
		items.add(new Item(2, PRICE));
		SpecialPrice specialPrice = mock(SpecialPrice.class);
		rules.add(new Buy2EqualsSpecialPriceRule(specialPrice));
		checkOut = new CheckOut(rules);

		receipt = checkOut.calculatePrice(items);

		assertEquals(PRICE, receipt.getItem(0).getPrice(), PRICE_DELTA);
		assertEquals(PRICE, receipt.getItem(1).getPrice(), PRICE_DELTA);

		assertEquals(PRICE * 2, receipt.getTotal(), PRICE_DELTA);
	}

	@Test
	public void buy2EqualsItems() {
		items.add(new Item(1, PRICE));
		items.add(new Item(1, PRICE));
		SpecialPrice specialPrice = new HalfPrice();
		rules.add(new Buy2EqualsSpecialPriceRule(specialPrice));
		checkOut = new CheckOut(rules);

		receipt = checkOut.calculatePrice(items);

		assertEquals(PRICE, receipt.getItem(0).getPrice(), PRICE_DELTA);
		assertEquals(PRICE / 2, receipt.getItem(1).getPrice(), PRICE_DELTA);

		assertEquals(PRICE + (PRICE / 2), receipt.getTotal(), PRICE_DELTA);
	}

	@Test
	public void buy3EqualsItems() {
		items.add(new Item(1, PRICE));
		items.add(new Item(1, PRICE));
		items.add(new Item(1, PRICE));
		SpecialPrice specialPrice = new HalfPrice();
		rules.add(new Buy2EqualsSpecialPriceRule(specialPrice));
		checkOut = new CheckOut(rules);

		receipt = checkOut.calculatePrice(items);

		assertEquals(PRICE, receipt.getItem(0).getPrice(), PRICE_DELTA);
		assertEquals(PRICE / 2, receipt.getItem(1).getPrice(), PRICE_DELTA);
		assertEquals(PRICE, receipt.getItem(2).getPrice(), PRICE_DELTA);

		assertEquals(PRICE * 2 + (PRICE / 2), receipt.getTotal(), PRICE_DELTA);
	}

	@Test
	public void buy4EqualsItems() {
		items.add(new Item(1, PRICE));
		items.add(new Item(1, PRICE));
		items.add(new Item(1, PRICE));
		items.add(new Item(1, PRICE));
		SpecialPrice specialPrice = new HalfPrice();
		rules.add(new Buy2EqualsSpecialPriceRule(specialPrice));
		checkOut = new CheckOut(rules);

		receipt = checkOut.calculatePrice(items);

		assertEquals(PRICE, receipt.getItem(0).getPrice(), PRICE_DELTA);
		assertEquals(PRICE / 2, receipt.getItem(1).getPrice(), PRICE_DELTA);
		assertEquals(PRICE, receipt.getItem(2).getPrice(), PRICE_DELTA);
		assertEquals(PRICE / 2, receipt.getItem(3).getPrice(), PRICE_DELTA);

		assertEquals(PRICE * 3, receipt.getTotal(), PRICE_DELTA);
	}

	@Test
	public void buySomeEqualsSomeDifferentItems() {
		items.add(new Item(1, PRICE));
		items.add(new Item(2, PRICE * 2));
		items.add(new Item(2, PRICE * 2));
		items.add(new Item(1, PRICE));
		SpecialPrice specialPrice = new HalfPrice();
		rules.add(new Buy2EqualsSpecialPriceRule(specialPrice));
		checkOut = new CheckOut(rules);

		receipt = checkOut.calculatePrice(items);

		assertEquals(PRICE, receipt.getItem(0).getPrice(), PRICE_DELTA);
		assertEquals(PRICE * 2, receipt.getItem(1).getPrice(), PRICE_DELTA);
		assertEquals(PRICE, receipt.getItem(2).getPrice(), PRICE_DELTA);
		assertEquals(PRICE / 2, receipt.getItem(3).getPrice(), PRICE_DELTA);

		assertEquals(PRICE * 4 + (PRICE / 2), receipt.getTotal(), PRICE_DELTA);
	}
}
