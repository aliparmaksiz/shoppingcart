package com.trendyol.shoppingcart.domain.rulediscount;

public class RuleDiscountAmount extends RuleDiscount{

	

	@Override
	public Double apply(Double totalCost, Double amount) {
		return amount.doubleValue();
	}

}
