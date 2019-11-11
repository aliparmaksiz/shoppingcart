package com.trendyol.shoppingcart.domain.spec;

public class MinimumItemLessThanSpec extends PredefinedRuleSpecification<Integer,Integer> {

	@Override
	public boolean test(Integer input, Integer comparedItem) {
		{
			if(input < comparedItem.intValue()) {
				return true;
			}
			return false;
		}
	}

}
