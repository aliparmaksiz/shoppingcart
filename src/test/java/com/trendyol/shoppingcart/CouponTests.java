package com.trendyol.shoppingcart;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.trendyol.shoppingcart.domain.base.PriceVO;
import com.trendyol.shoppingcart.domain.base.enumeration.DiscountType;
import com.trendyol.shoppingcart.domain.category.Category;
import com.trendyol.shoppingcart.domain.coupon.Coupon;
import com.trendyol.shoppingcart.domain.product.Product;
import com.trendyol.shoppingcart.domain.shoppingcart.ShoppingCart;

class CouponTests {
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
		Mockito.doReturn(100.0).when(orangePriceVO).getPrice();
		Mockito.doReturn(120.0).when(applePriceVO).getPrice();
		Mockito.doReturn(150.0).when(almondPriceVO).getPrice();
		products.add(apple);
		products.add(almond);
		products.add(orange);
	}

	@Test
	public void whenCouponsHaveDiscount() {
		// GIVEN
		Coupon coupon = new Coupon(100, 10, DiscountType.RATE);
		int almondSize = 10;
		int appleSize = 5;
		int orangeSize = 2;
		double totalCost = almond.getPrice().getPrice() * almondSize + apple.getPrice().getPrice() * appleSize
				+ orange.getPrice().getPrice() * orangeSize;
		double couponDiscount = coupon.calculateTotalDiscount(totalCost);
		// WHEN
		cart.addItem(almond, almondSize);
		cart.addItem(orange, orangeSize);
		cart.addItem(apple, appleSize);
		cart.applyCoupon(coupon);
		// THEN
		Assertions.assertEquals(cart.getCouponDiscount(), couponDiscount);
	}

	@Test
	public void whenCouponsDoesNotHaveDiscount() {
		// GIVEN
		Coupon coupon = new Coupon(1000, 10, DiscountType.RATE);
		int almondSize = 1;
		int appleSize = 5;
		int orangeSize = 2;
		// WHEN
		cart.addItem(almond, almondSize);
		cart.addItem(orange, orangeSize);
		cart.addItem(apple, appleSize);
		cart.applyCoupon(coupon);
		// THEN
		Assertions.assertEquals(cart.getCouponDiscount(), 0.0);
	}

}
