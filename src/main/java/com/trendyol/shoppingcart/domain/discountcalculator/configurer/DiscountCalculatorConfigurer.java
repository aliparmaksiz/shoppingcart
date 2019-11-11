package com.trendyol.shoppingcart.domain.discountcalculator.configurer;

import com.trendyol.shoppingcart.domain.rulediscount.RuleDiscount;
import com.trendyol.shoppingcart.domain.spec.PredefinedRuleSpecification;

public class DiscountCalculatorConfigurer<T extends RuleDiscount, E extends PredefinedRuleSpecification<?,?>> {
	  
		private T ruleDiscount;
		private E predefinedSpecification;
		
		private DiscountCalculatorConfigurer(T campaignRuleDiscount, E minimumItemSpecification) {
			this.ruleDiscount = campaignRuleDiscount;
			this.predefinedSpecification = minimumItemSpecification;
		}
	
	    public DiscountCalculatorConfigurer() {
		}

		public  Builder custom() {
	        return new Builder();
	    }


	    public T getRuleDiscount() {
	        return ruleDiscount;
	    }

	    public E getPredefinedSpecification() {
	        return predefinedSpecification;
	    }

	    public  class Builder {

	    	private T ruleDiscount;
			private E predefinedSpecification;
			
			private Builder() {
				
			}

		    public Builder ruleDiscount(T ruleDiscount) {
		        this.ruleDiscount = ruleDiscount ;
		        return this;
		    }

		    public Builder  predefinedSpecification(E minimumItemSpecification) {
		        this.predefinedSpecification = minimumItemSpecification;
				return this;
		    }
			
	        public DiscountCalculatorConfigurer<T,E> build() {
	            return new DiscountCalculatorConfigurer<T, E>(ruleDiscount,predefinedSpecification);
	        }
}
}
