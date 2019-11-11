package com.trendyol.shoppingcart.domain.shoppingcart;

import com.trendyol.shoppingcart.domain.category.Category;
import com.trendyol.shoppingcart.domain.product.Product;

public class ShoppingItem {
	private Product product; 
	private int quantity;
	private Category category;
	
	ShoppingItem(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
		this.category = product.getCategory();
	}
	
	public Product getProduct() {
		return product;
	}
	

	public int getQuantity() {
		return quantity;
	}

	public Category getCategory() {
		return category;
	}

}
