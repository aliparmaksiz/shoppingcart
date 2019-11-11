package com.trendyol.shoppingcart.domain.category;

import java.util.ArrayList;
import java.util.List;

import com.trendyol.shoppingcart.domain.product.Product;

public class Category {
	
	private CategoryTitleVO categoryTitle;
	private List<Product> products = new ArrayList<Product>();
	
	public List<Product> getProducts() {
		return products;
	}


	public Category(String val) {
		this.categoryTitle=new CategoryTitleVO(val);
	}

	
	public CategoryTitleVO getCategoryTitle() {
		return categoryTitle;	
	}


	public void addProduct(Product product) {
		products.add(product);
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoryTitle == null) ? 0 : categoryTitle.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (categoryTitle == null) {
			if (other.categoryTitle != null)
				return false;
		} else if (!categoryTitle.equals(other.categoryTitle))
			return false;
		return true;
	}

}
