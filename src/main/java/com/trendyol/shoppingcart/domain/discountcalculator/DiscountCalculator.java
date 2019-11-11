package com.trendyol.shoppingcart.domain.discountcalculator;

import com.trendyol.shoppingcart.domain.discountcalculator.configurer.DiscountCalculatorConfigurer;
import com.trendyol.shoppingcart.domain.rulediscount.RuleDiscount;
import com.trendyol.shoppingcart.domain.spec.PredefinedRuleSpecification;

public abstract class DiscountCalculator<T extends RuleDiscount, I, O, E extends PredefinedRuleSpecification<I,O>> {
	
	private DiscountCalculatorConfigurer<T,E> configurer;
	

	public DiscountCalculator(DiscountCalculatorConfigurer<T,E> configurer) {
		this.configurer=configurer;
	}
	
	public DiscountCalculator() {
	}
	
	protected E getSpecification() {
		return configurer.getPredefinedSpecification();
	}

	protected abstract boolean validate(I input,  O compare);
	
	public double applyCampaign(double totalCost, double applicableDiscountVal, I minimumItemNumber, O allItems) {
		if(validate(minimumItemNumber, allItems)) {
			return configurer.getRuleDiscount().apply(totalCost,applicableDiscountVal);
		}
		return 0.0;
	}
	
	public double applyCoupon(I totalCost, O minCost, double applicableDiscountVal) {
		if(validate(totalCost, minCost)) {
			return configurer.getRuleDiscount().apply((Double) totalCost,applicableDiscountVal);
		}
		return 0.0;
	}
}

