package com.trendyol.shoppingcart.domain.coupon;

import com.trendyol.shoppingcart.domain.base.enumeration.DiscountType;
import com.trendyol.shoppingcart.domain.discountcalculator.DiscountCalculator;
import com.trendyol.shoppingcart.domain.rulediscount.RuleDiscount;
import com.trendyol.shoppingcart.domain.spec.PredefinedRuleSpecification;

public class Coupon {
	
	DiscountCalculator<? extends RuleDiscount, Double, Double , ? extends PredefinedRuleSpecification<Double, Double>> discountCalculator;
	private double minCost;
	private double ratio;
	
	public Coupon(double minCost, double ratio, DiscountType discountType) {
		this.discountCalculator = discountType.getCouponDiscountCalculator();
		this.minCost =minCost;
		this.ratio=ratio;
	}
	
	
	public double calculateTotalDiscount(double totalCost) {
		return discountCalculator.applyCoupon(totalCost,minCost, ratio);
	}
}
