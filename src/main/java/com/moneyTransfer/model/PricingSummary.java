package com.moneyTransfer.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author luis.stubbia
 *
 */
public class PricingSummary {

	private Map<TransactionType, BigDecimal> prices;

	/* Add any new price summary required */

	/**
	 * Default constructor.
	 */
	public PricingSummary() {
		prices = new HashMap<TransactionType, BigDecimal>(TransactionType.values().length);
		for (TransactionType type : TransactionType.values()) {
			prices.put(type, BigDecimal.ZERO);
		}
	}

	public Map<TransactionType, BigDecimal> getPrices() {
		return prices;
	}

	public void setPrices(Map<TransactionType, BigDecimal> prices) {
		this.prices = prices;
	}

	public static PricingSummary build(Set<Transaction> movements) {
		PricingSummary pricingSummary = new PricingSummary();
		if (movements != null && !movements.isEmpty()) {
			movements.forEach(mov -> {
				BigDecimal amount = pricingSummary.getPrices().get(mov.getType());
				if (amount != null) {
					amount.add(mov.getAmount());
				}
			});
		}
		return pricingSummary;
	}
}
