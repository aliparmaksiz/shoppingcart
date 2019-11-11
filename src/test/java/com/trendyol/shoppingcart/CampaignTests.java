package com.trendyol.shoppingcart;

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
import com.trendyol.shoppingcart.domain.product.Product;
import com.trendyol.shoppingcart.domain.shoppingcart.ShoppingCart;

class CampaignTests {
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
	public void whenCampaignsDoesNotDiscount() {
		// GIVEN
	
		Campaign campaign1 = new Campaign(firstCategory, 20.0, 3, DiscountType.RATE);
		Campaign campaign2 = new Campaign(secondCategory, 50.0, 5, DiscountType.RATE);
		Campaign campaign3 = new Campaign(thirdCategory, 5.0, 5, DiscountType.AMOUNT);
		// WHEN
		cart.addItem(almond, 1);
		cart.addItem(orange, 1);
		cart.addItem(apple, 1);
		cart.applyCampaignDiscounts(campaign1, campaign2, campaign3);
		// THEN
		Assertions.assertEquals(cart.getCampaignDiscount(), 0.0);
	}

	@Test
	public void whenCampaignsHaveDiscount() {
	
		Campaign campaign1 = new Campaign(firstCategory, 20.0, 3, DiscountType.RATE);
		Campaign campaign2 = new Campaign(secondCategory, 50.0, 5, DiscountType.RATE);
		Campaign campaign3 = new Campaign(thirdCategory, 5.0, 5, DiscountType.AMOUNT);
		int almondSize = 3;
		int appleSize = 5;
		int orangeSize = 2;

		double almondDiscount = campaign2.calculateTotalDiscount(almond.getPrice().getPrice() * almondSize, almondSize);
		double appleDiscount = campaign1.calculateTotalDiscount(apple.getPrice().getPrice() * appleSize, appleSize);
		double orangeDiscount = campaign3.calculateTotalDiscount(orange.getPrice().getPrice() * orangeSize, orangeSize);
		double maxDiscount = Math.max(Math.max(almondDiscount, appleDiscount), orangeDiscount);
		
		// WHEN
		cart.addItem(almond, almondSize);
		cart.addItem(orange, orangeSize);
		cart.addItem(apple, appleSize);
		cart.applyCampaignDiscounts(campaign1, campaign2, campaign3);
		// THEN
		Assertions.assertEquals(cart.getCampaignDiscount(), maxDiscount);
	}

}
