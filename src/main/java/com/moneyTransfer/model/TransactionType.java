package com.moneyTransfer.model;

/**
 * 
 * @author luis.stubbia
 *
 */
public enum TransactionType {

	DEBIT("DEBIT"), CREDIT("CREDIT");

	private String name;

	TransactionType(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}
