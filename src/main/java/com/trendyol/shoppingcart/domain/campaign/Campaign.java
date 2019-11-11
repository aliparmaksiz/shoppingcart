package com.trendyol.shoppingcart.domain.campaign;

import com.trendyol.shoppingcart.domain.base.enumeration.DiscountType;
import com.trendyol.shoppingcart.domain.category.Category;
import com.trendyol.shoppingcart.domain.discountcalculator.DiscountCalculator;
import com.trendyol.shoppingcart.domain.rulediscount.RuleDiscount;
import com.trendyol.shoppingcart.domain.spec.PredefinedRuleSpecification;

public class Campaign {
	
	DiscountCalculator<? extends RuleDiscount, Integer,Integer ,? extends PredefinedRuleSpecification<Integer,Integer>> campaignDiscountCalculator;
	private int minimumItemNumber;
	private double value;
	private Category category;
	
	public Campaign(Category category, double value, int minimumItemNumber, DiscountType discountType) {
		this.campaignDiscountCalculator = discountType.getCampaignDiscountCalculator();
		this.minimumItemNumber = minimumItemNumber;
		this.value =value;
		this.category = category;
	}
	
	
	public double calculateTotalDiscount(double totalCost, int allItems) {
		return campaignDiscountCalculator.applyCampaign(totalCost, this.value, minimumItemNumber, allItems);
	}
	
	public Category getCategory() {
		return category;
	}


}
