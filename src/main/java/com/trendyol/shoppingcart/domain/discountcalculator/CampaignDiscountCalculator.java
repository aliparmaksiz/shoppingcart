package com.trendyol.shoppingcart.domain.discountcalculator;

import com.trendyol.shoppingcart.domain.discountcalculator.configurer.DiscountCalculatorConfigurer;
import com.trendyol.shoppingcart.domain.rulediscount.RuleDiscount;
import com.trendyol.shoppingcart.domain.spec.PredefinedRuleSpecification;

public class CampaignDiscountCalculator extends DiscountCalculator<RuleDiscount, Integer,Integer, PredefinedRuleSpecification<Integer, Integer>>{

	public CampaignDiscountCalculator(
			DiscountCalculatorConfigurer<RuleDiscount, PredefinedRuleSpecification<Integer, Integer>> campaignDiscountConfigurer) {
		super(campaignDiscountConfigurer);
	}

	@Override
	protected boolean validate(Integer minimumItemNumber, Integer allItems) {
		return getSpecification().test(minimumItemNumber, allItems);
	}

}
