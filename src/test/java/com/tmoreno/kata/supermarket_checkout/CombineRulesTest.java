package com.tmoreno.kata.supermarket_checkout;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.tmoreno.kata.supermarket_checkout.rules.Buy2EqualsSpecialPriceRule;
import com.tmoreno.kata.supermarket_checkout.rules.Buy3Equals1FreeRule;
import com.tmoreno.kata.supermarket_checkout.rules.Buy3ItemsSetCheapestFreeRule;
import com.tmoreno.kata.supermarket_checkout.rules.Rule;
import com.tmoreno.kata.supermarket_checkout.specialprice.HalfPrice;
import com.tmoreno.kata.supermarket_checkout.specialprice.SpecialPrice;

public class CombineRulesTest {

	private static final double PRICE_DELTA = 0L;
	private static final double PRICE = 10L;

	private List<Item> items;
	private List<Rule> rules;
	private CheckOut checkOut;
	private Receipt receipt;
	private Set<Integer> itemOnSet;

	@Before
	public void setUp() {
		items = new ArrayList<>();
		rules = new ArrayList<>();

		itemOnSet = new HashSet<>();
		itemOnSet.add(1);
		itemOnSet.add(2);
		itemOnSet.add(3);
	}

	@Test
	public void buySomeEqualsSomeDifferentItems() {
		items.add(new Item(1, PRICE));
		items.add(new Item(2, PRICE * 2));
		items.add(new Item(2, PRICE * 2));
		items.add(new Item(1, PRICE));

		SpecialPrice specialPrice = new HalfPrice();
		rules.add(new Buy2EqualsSpecialPriceRule(specialPrice));
		rules.add(new Buy3Equals1FreeRule());
		rules.add(new Buy3ItemsSetCheapestFreeRule(itemOnSet));
		checkOut = new CheckOut(rules);

		receipt = checkOut.calculatePrice(items);

		assertEquals(PRICE, receipt.getItem(0).getPrice(), PRICE_DELTA);
		assertEquals(PRICE * 2, receipt.getItem(1).getPrice(), PRICE_DELTA);
		assertEquals(PRICE, receipt.getItem(2).getPrice(), PRICE_DELTA);
		assertEquals(PRICE / 2, receipt.getItem(3).getPrice(), PRICE_DELTA);

		assertEquals(PRICE * 4 + (PRICE / 2), receipt.getTotal(), PRICE_DELTA);
	}
}
