package com.lines_of_code.gsa.stockwatcher.client;

import com.google.gwt.junit.client.GWTTestCase;

public class StockWatcherTest extends GWTTestCase {

	/**
	 * Must refer to a valid module that sources this class.
	 */
	@Override
	public String getModuleName() {
		return "com.lines_of_code.gsa.stockwatcher.StockWatcher";
	}

	/**
	 * Verify that the instance fields in the StockPrice class are set
	 * correctly.
	 */
	public void testStockPriceCtor() {
		String symbol = "XYZ";
		double price = 70.0;
		double change = 2.0;
		double changePercent = 100.0 * change / price;

		StockPrice sp = new StockPrice(symbol, price, change);
		assertNotNull(sp);
		assertEquals(symbol, sp.getSymbol());
		assertEquals(price, sp.getPrice(), 0.001);
		assertEquals(change, sp.getChange(), 0.001);
		assertEquals(changePercent, sp.getChangePercent(), 0.001);

	}
}
