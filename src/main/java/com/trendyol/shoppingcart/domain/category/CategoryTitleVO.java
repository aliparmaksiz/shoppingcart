package com.trendyol.shoppingcart.domain.category;

import com.trendyol.shoppingcart.domain.base.TitleVO;

public class CategoryTitleVO extends TitleVO{

	protected CategoryTitleVO(String val) {
		super(val);
		
	}

	@Override
	protected void validate(String val) {
		//you can implement category title specific value validation
	}

}
