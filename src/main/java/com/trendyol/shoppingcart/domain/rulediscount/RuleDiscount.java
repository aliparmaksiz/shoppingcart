package com.trendyol.shoppingcart.domain.rulediscount;

import java.util.function.BiFunction;

public abstract class RuleDiscount implements BiFunction<Double, Double, Double> {

	@Override
	public abstract Double apply(Double totalCost, Double applicableValue);
	

}
