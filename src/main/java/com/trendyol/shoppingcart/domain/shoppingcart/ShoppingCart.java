package com.trendyol.shoppingcart.domain.shoppingcart;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.trendyol.shoppingcart.domain.campaign.Campaign;
import com.trendyol.shoppingcart.domain.category.Category;
import com.trendyol.shoppingcart.domain.coupon.Coupon;
import com.trendyol.shoppingcart.domain.product.Product;

public class ShoppingCart {
	
	
	private long id;
	private List<ShoppingItem> items ;
	private double totalAmountAfterDiscount;
	private double campaignDiscount;
	private double couponDiscount;
	private double deliveryCost;


	public ShoppingCart() {
		Random r = new Random();
		id= r.nextLong();
		items= new ArrayList<ShoppingItem>();
	}
	
	public void addItem(Product product, int quantity) {
		ShoppingItem item = new ShoppingItem(product, quantity);
		items.add(item);
		totalAmountAfterDiscount =totalAmountAfterDiscount + product.getPrice().getPrice()*quantity;
	}
	
	public void applyCampaignDiscounts(Campaign... campaigns) {
		Map<Category, List<ShoppingItem>> shoppingItemsByCategory = items.stream().collect(Collectors.groupingBy(ShoppingItem::getCategory));
		campaignDiscount= shoppingItemsByCategory.entrySet().stream().map(entry-> updateShoppingCartTotalCostByCampaign(Stream.of(campaigns),entry)).max(Comparator.naturalOrder()).get();
		totalAmountAfterDiscount = totalAmountAfterDiscount-campaignDiscount;
	}
	
	public void applyCoupon(Coupon coupon) {
		couponDiscount = coupon.calculateTotalDiscount(totalAmountAfterDiscount);
		totalAmountAfterDiscount = totalAmountAfterDiscount-couponDiscount;
	}

	private double updateShoppingCartTotalCostByCampaign(Stream<Campaign> campaignStream,
			Entry<Category, List<ShoppingItem>> entry) {
		
		Integer allItems = entry.getValue().stream().map(item->item.getQuantity()).reduce(0, Integer::sum);
		Double totalCost = entry.getValue().stream().map(item->item.getProduct().getPrice().getPrice()*item.getQuantity()).reduce(0.0, (p1,p2)->p1+p2);
		double campaignDiscount = campaignStream.filter(campaign->campaign.getCategory().equals(entry.getKey())).map(campaign->campaign.calculateTotalDiscount(totalCost, allItems)).findFirst().get();
		return campaignDiscount;
	}
	
	public double getCouponDiscount() {
		return couponDiscount;
	}

	public double getCampaignDiscount() {
		return campaignDiscount;
	}
	
	public List<ShoppingItem> getItems() {
		return items;
	}
	

	public double getDeliveryCost() {
		return deliveryCost;
	}

	public void setDeliveryCost(Double deliveryCost) {
		this.deliveryCost = deliveryCost;
	}
	
	public void print() {
		Map<Category, List<Product>> productItemsByCategory = getItems().stream().map(item->item.getProduct()).collect(Collectors.groupingBy(Product::getCategory));
		Map<Category, List<ShoppingItem>> shoppingItemsByCategory = getItems().stream().collect(Collectors.groupingBy(ShoppingItem::getCategory));
		productItemsByCategory.forEach((k,v)-> { 
				System.out.println("Category Name: "+k.getCategoryTitle().getVal());
				shoppingItemsByCategory.get(k).stream().forEach(item->{
					System.out.println("Product title:" + item.getProduct().getTitle().getVal()); 
					System.out.println("Product price:" + item.getProduct().getPrice().getPrice());
					System.out.println("Product quantity:" + item.getQuantity());
					System.out.println("Product total price: "+item.getProduct().getPrice().getPrice()*item.getQuantity());
				});
		});
		
		System.out.println("Total discount" + getCampaignDiscount()+getCouponDiscount());
		System.out.println("Total cart" + getTotalAmountAfterDiscount());
		
	}

	public double getTotalAmountAfterDiscount() {
		return totalAmountAfterDiscount;
	}
	
}
