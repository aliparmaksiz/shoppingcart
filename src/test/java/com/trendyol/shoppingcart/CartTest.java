package com.trendyol.shoppingcart;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.trendyol.shoppingcart.domain.base.PriceVO;
import com.trendyol.shoppingcart.domain.base.enumeration.DiscountType;
import com.trendyol.shoppingcart.domain.campaign.Campaign;
import com.trendyol.shoppingcart.domain.category.Category;
import com.trendyol.shoppingcart.domain.category.CategoryTitleVO;
import com.trendyol.shoppingcart.domain.coupon.Coupon;
import com.trendyol.shoppingcart.domain.product.Product;
import com.trendyol.shoppingcart.domain.shoppingcart.ShoppingCart;

public class CartTest {
	@Mock
	Category firstCategory;
	@Mock
	Category secondCategory;
	@Mock
	Category thirdCategory;
	@Mock
	CategoryTitleVO categoryTitleVO;
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
		Mockito.doReturn(firstCategory).when(apple).getCategory();
		Mockito.doReturn(secondCategory).when(almond).getCategory();
		Mockito.doReturn(thirdCategory).when(orange).getCategory();
		Mockito.doReturn(categoryTitleVO).when(firstCategory).getCategoryTitle();
		Mockito.doReturn(categoryTitleVO).when(secondCategory).getCategoryTitle();
		Mockito.doReturn(categoryTitleVO).when(thirdCategory).getCategoryTitle();
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
	public void printResults() {
		// GIVEN
		ShoppingCart cart = Mockito.spy(ShoppingCart.class);
		// WHEN
		Mockito.doNothing().when(cart).addItem(Mockito.any(Product.class), Mockito.anyInt());
		cart.addItem(Mockito.mock(Product.class), 1);
		cart.addItem(Mockito.mock(Product.class), 1);
		cart.addItem(Mockito.mock(Product.class), 1);
		cart.print();
		// THEN
		verify(cart, atLeast(2)).getItems();
		verify(cart, atLeast(1)).getCampaignDiscount();
		verify(cart, atLeast(1)).getCouponDiscount();
	}

	@Test
	public void getTotalAmountAfterDiscount() {
		// GIVEN
		Coupon coupon = new Coupon(100, 10, DiscountType.RATE);
		Campaign campaign1 = new Campaign(firstCategory, 20.0, 3, DiscountType.RATE);
		Campaign campaign2 = new Campaign(secondCategory, 50.0, 5, DiscountType.RATE);
		Campaign campaign3 = new Campaign(thirdCategory, 5.0, 5, DiscountType.AMOUNT);
		int almondSize = 10;
		int appleSize = 5;
		int orangeSize = 2;
		cart.addItem(almond, almondSize);
		cart.addItem(orange, orangeSize);
		cart.addItem(apple, appleSize);
		double totalAmountBeforeDiscount = cart.getTotalAmountAfterDiscount();
		// WHEN
		cart.applyCampaignDiscounts(campaign1, campaign2, campaign3);
		cart.applyCoupon(coupon);
		Assertions.assertEquals(cart.getTotalAmountAfterDiscount(),
				totalAmountBeforeDiscount - (cart.getCampaignDiscount() + cart.getCouponDiscount()));
	}

}
