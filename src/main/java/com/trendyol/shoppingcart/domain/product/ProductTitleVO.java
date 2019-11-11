package com.trendyol.shoppingcart.domain.product;

import com.trendyol.shoppingcart.domain.base.TitleVO;

public class ProductTitleVO extends TitleVO{

	protected ProductTitleVO(String val) {
		super(val);
		
	}

	@Override
	protected void validate(String val) {
		//you can implement product title specific value validation
	}

}
