package com.lines_of_code.gsa.stockwatcher.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface StockPriceServiceAsync {

	void getPrices(String[] symbols, AsyncCallback<StockPrice[]> callback);

}
