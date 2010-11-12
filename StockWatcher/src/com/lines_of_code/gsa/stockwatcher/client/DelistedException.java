package com.lines_of_code.gsa.stockwatcher.client;

import java.io.Serializable;

public class DelistedException extends Exception implements Serializable {

	private String symbol;

	public DelistedException() {
	}

	public DelistedException(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}
}
