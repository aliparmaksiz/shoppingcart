package com.trendyol.shoppingcart.domain.base.enumeration;

import com.trendyol.shoppingcart.domain.discountcalculator.CampaignDiscountCalculator;
import com.trendyol.shoppingcart.domain.discountcalculator.CouponDiscountCalculator;
import com.trendyol.shoppingcart.domain.discountcalculator.DiscountCalculator;
import com.trendyol.shoppingcart.domain.discountcalculator.configurer.DiscountCalculatorConfigurer;
import com.trendyol.shoppingcart.domain.rulediscount.RuleDiscount;
import com.trendyol.shoppingcart.domain.rulediscount.RuleDiscountAmount;
import com.trendyol.shoppingcart.domain.rulediscount.RuleDiscountRatio;
import com.trendyol.shoppingcart.domain.spec.MinimumItemLessThanSpec;
import com.trendyol.shoppingcart.domain.spec.MinimumSpentLessThanSpec;
import com.trendyol.shoppingcart.domain.spec.PredefinedRuleSpecification;

public enum DiscountType {
	RATE {
		@Override
		public <T extends RuleDiscount, I,O,E extends PredefinedRuleSpecification<I,O>> DiscountCalculatorConfigurer<T, E> getCampaignDiscountConfigurer() {
			return (DiscountCalculatorConfigurer<T, E>) new DiscountCalculatorConfigurer<RuleDiscountRatio, MinimumItemLessThanSpec>()
			.custom()
			.ruleDiscount(new RuleDiscountRatio())
			.predefinedSpecification(new MinimumItemLessThanSpec()).build();
		}

		@Override
		public <T extends RuleDiscount, I, O, E extends PredefinedRuleSpecification<I, O>> DiscountCalculatorConfigurer<T, E> getCouponDiscountConfigurer() {
			return (DiscountCalculatorConfigurer<T, E>) new DiscountCalculatorConfigurer<RuleDiscountRatio, MinimumSpentLessThanSpec>()
					.custom()
					.ruleDiscount(new RuleDiscountRatio())
					.predefinedSpecification(new MinimumSpentLessThanSpec()).build();
		}
	}, AMOUNT {
		@Override
		public <T extends RuleDiscount, I,O, E extends PredefinedRuleSpecification<I,O>> DiscountCalculatorConfigurer<T, E> getCampaignDiscountConfigurer() {
			return (DiscountCalculatorConfigurer<T, E>) new DiscountCalculatorConfigurer<RuleDiscountAmount, MinimumItemLessThanSpec>()
					.custom()
					.ruleDiscount(new RuleDiscountAmount())
					.predefinedSpecification(new MinimumItemLessThanSpec()).build();
		}

		@Override
		public <T extends RuleDiscount, I, O, E extends PredefinedRuleSpecification<I, O>> DiscountCalculatorConfigurer<T, E> getCouponDiscountConfigurer() {
			return (DiscountCalculatorConfigurer<T, E>) new DiscountCalculatorConfigurer<RuleDiscountAmount, MinimumSpentLessThanSpec>()
					.custom()
					.ruleDiscount(new RuleDiscountAmount())
					.predefinedSpecification(new MinimumSpentLessThanSpec()).build();
		}
	} ;
	
	
	public abstract <T extends RuleDiscount,I,O, E extends PredefinedRuleSpecification<I,O>> DiscountCalculatorConfigurer<T,E> getCampaignDiscountConfigurer();
	public abstract <T extends RuleDiscount, I,O, E extends PredefinedRuleSpecification<I,O>> DiscountCalculatorConfigurer<T,E> getCouponDiscountConfigurer();
	
	public <T extends RuleDiscount, I, O, E extends PredefinedRuleSpecification<I,O>> DiscountCalculator<T, I,O,E> getCampaignDiscountCalculator() {
			return (DiscountCalculator<T, I, O, E>) new CampaignDiscountCalculator(getCampaignDiscountConfigurer());
	}
	
	public <T extends RuleDiscount, I, O, E extends PredefinedRuleSpecification<I,O>> DiscountCalculator<T, I,O,E> getCouponDiscountCalculator() {
		return (DiscountCalculator<T, I, O, E>) new CouponDiscountCalculator(getCouponDiscountConfigurer());
	}
}

