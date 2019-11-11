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
import com.trendyol.shoppingcart.domain.delivery.DeliveryCostCalculator;
import com.trendyol.shoppingcart.domain.product.Product;
import com.trendyol.shoppingcart.domain.shoppingcart.ShoppingCart;

public class DeliveryTests {

	@Mock
	Category firstCategory;
	@Mock
	Category secondCategory;
	@Mock
	Category thirdCategory;
	@Mock
	Product apple;
	@Mock
	Product almond;
	@Mock
	Product orange;
	@Mock
	PriceVO applePriceVO;
	@Mock
	PriceVO almondPriceVO;
	@Mock
	PriceVO orangePriceVO;
	@Spy
	private ArrayList<Product> products;
	@Spy
	private ShoppingCart cart;
	
	@BeforeEach
	void contextLoads() {
		MockitoAnnotations.initMocks(this);
		Mockito.doReturn(applePriceVO).when(apple).getPrice();
		Mockito.doReturn(almondPriceVO).when(almond).getPrice();
		Mockito.doReturn(orangePriceVO).when(orange).getPrice();
		products.add(apple);
		products.add(almond);
		products.add(orange);
	}
	
	@Test
	public void whenDeliveryHappens() {

		int almondSize = 1;
		int appleSize = 5;
		int orangeSize = 2;
		products = (ArrayList<Product>) products.stream().sorted(Comparator.comparing(Product::getId)).collect(Collectors.toList());
	
		// WHEN
		cart.addItem(almond, almondSize);
		cart.addItem(orange, orangeSize);
		cart.addItem(apple, appleSize);

		DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculator(2.0, 4.0, 2.99);
		DeliveryCostCalculator deliveryCostCalculatorSpy=Mockito.spy(deliveryCostCalculator);
		double deliveryCost = deliveryCostCalculatorSpy.calculateFor(cart);

		// THEN
		Assertions.assertEquals(cart.getDeliveryCost(), deliveryCost);
	}

}
