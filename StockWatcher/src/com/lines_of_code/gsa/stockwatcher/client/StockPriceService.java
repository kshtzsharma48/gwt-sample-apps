package com.lines_of_code.gsa.stockwatcher.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

//Associates the service with a default path relaitve to the module base URL.
@RemoteServiceRelativePath("stockPrices")
public interface StockPriceService extends RemoteService {

	StockPrice[] getPrices(String[] symbols) throws DelistedException;
}
