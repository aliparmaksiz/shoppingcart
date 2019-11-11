package com.trendyol.shoppingcart.domain.rulediscount;

public class RuleDiscountRatio extends RuleDiscount{

	@Override
	public Double apply(Double totalCost, Double ratio) {
		return (totalCost.doubleValue()*ratio.doubleValue())/100.0;
	}

}
