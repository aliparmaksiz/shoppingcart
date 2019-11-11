package com.trendyol.shoppingcart;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.trendyol.shoppingcart.domain.base.PriceVO;
import com.trendyol.shoppingcart.domain.category.Category;
import com.trendyol.shoppingcart.domain.product.Product;
import com.trendyol.shoppingcart.domain.shoppingcart.ShoppingCart;
import com.trendyol.shoppingcart.domain.shoppingcart.ShoppingItem;

class ProductTests {

	@Mock
	Category firstCategory;
	@Mock
	Category secondCategory;
	@Mock
	Product apple;
	@Mock
	Product almond;
	@Mock
	PriceVO priceVO;
	
	@Spy
	private ArrayList<Product> products;
	@Spy
	private ShoppingCart cart;

	@BeforeEach
	void contextLoads() {
		// GIVEN
		MockitoAnnotations.initMocks(this);
		Mockito.doReturn(priceVO).when(apple).getPrice();
		Mockito.doReturn(priceVO).when(almond).getPrice();
		Mockito.doReturn(new Double("10")).when(priceVO).getPrice();
		products.add(apple);
		products.add(almond);
	}

	@Test
	public void whenProductsAddedCheckCartSize() {
		
		// WHEN
		cart.addItem(apple, 4);
		cart.addItem(almond, 7);

		// THEN
		Assertions.assertEquals(cart.getItems().size(), products.size());
	}

	@Test
	public void whenProductAddedToCartCheckProducts() {

		Mockito.doReturn(1).when(apple).getId();
		Mockito.doReturn(2).when(almond).getId();
		products = (ArrayList<Product>) products.stream().sorted(Comparator.comparing(Product::getId)).collect(Collectors.toList());
		
		// WHEN
		cart.addItem(almond, 7);
		cart.addItem(apple, 4);

		// THEN
		Assertions.assertEquals(cart.getItems().stream().map(ShoppingItem::getProduct)
				.sorted(Comparator.comparing(Product::getId)).collect(Collectors.toList()), products);
	}

}
