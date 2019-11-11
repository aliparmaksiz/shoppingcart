package com.trendyol.shoppingcart.domain.spec;

public class MinimumSpentLessThanSpec extends PredefinedRuleSpecification<Double,Double> {

	@Override
	public boolean test(Double input, Double comparedItem) {
		{
			if(input.compareTo(comparedItem)>0) {
				return true;
			}
			return false;
		}
	}

}
