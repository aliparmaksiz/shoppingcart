package com.trendyol.shoppingcart.domain.discountcalculator;

import com.trendyol.shoppingcart.domain.discountcalculator.configurer.DiscountCalculatorConfigurer;
import com.trendyol.shoppingcart.domain.rulediscount.RuleDiscount;
import com.trendyol.shoppingcart.domain.spec.PredefinedRuleSpecification;

public class CouponDiscountCalculator extends DiscountCalculator<RuleDiscount, Double, Double, PredefinedRuleSpecification<Double, Double>>{

	public CouponDiscountCalculator(
			DiscountCalculatorConfigurer<RuleDiscount, PredefinedRuleSpecification<Double, Double>> couponDiscountConfigurer) {
		super(couponDiscountConfigurer);
	}

	@Override
	protected boolean validate(Double totalCost, Double comparedCostVal) {
		return getSpecification().test(totalCost, comparedCostVal);	
	}

}
