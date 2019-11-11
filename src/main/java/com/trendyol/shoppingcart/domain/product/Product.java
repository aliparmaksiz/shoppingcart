package com.trendyol.shoppingcart.domain.product;

import java.util.Random;

import com.trendyol.shoppingcart.domain.base.PriceVO;
import com.trendyol.shoppingcart.domain.category.Category;

public class Product {
	
	private int id;
	private ProductTitleVO title;
	private PriceVO price;
	private Category category;
	
	public Product(String title, double price, Category category) {
		this.title = new ProductTitleVO(title);
		this.price = new PriceVO(price); 
		this.category =category;
		this.id=new Random().nextInt();
		category.addProduct(this);
	}

	public ProductTitleVO getTitle() {
		return title;
	}

	public PriceVO getPrice() {
		return price;
	}


	public Category getCategory() {
		return category;
	}
	
	public int getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + id;
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Product other = (Product) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (id != other.id)
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}



}
