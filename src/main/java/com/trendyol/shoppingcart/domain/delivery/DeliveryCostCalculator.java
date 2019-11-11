package com.trendyol.shoppingcart.domain.delivery;

import java.util.Arrays;
import java.util.stream.DoubleStream;

import com.trendyol.shoppingcart.domain.shoppingcart.ShoppingCart;

public class DeliveryCostCalculator {
	
	private Double costPerDelivery;
	private Double costPerProduct;
	private final Double fixedCost;
	
	public DeliveryCostCalculator(Double costPerDelivery, Double costPerProduct, Double fixedCost) {
		this.costPerDelivery = costPerDelivery;
		this.costPerProduct =  costPerProduct;
		this.fixedCost = fixedCost;
	}
	
	
	public double calculateFor(ShoppingCart cart) {
		long numberOfDeliveries = findNumberOfDeliveries(cart);
		long numberOfProducts = findNumberOfProducts(cart);
		
		DeliveryCalculator numberOfDeliviriesCost = (costPerDeliveryParam, numberOfDeliveriesParam ) -> costPerDeliveryParam*numberOfDeliveriesParam;
		DeliveryCalculator numberOfProductsCost = (costPerProductParam, numberOfProductsCostParam) -> costPerProductParam*numberOfProductsCostParam;
		
		Double firstCost = numberOfDeliviriesCost.apply(costPerDelivery,Math.toIntExact(numberOfDeliveries));
		Double secondCost =  numberOfProductsCost.apply(costPerProduct,Math.toIntExact(numberOfProducts));
		Double deliveryCost = sumCost(firstCost, secondCost);
		
		cart.setDeliveryCost(deliveryCost);
		return deliveryCost.doubleValue();
	}


	private Double  sumCost(Double... costs) {
		return Arrays.stream(costs).reduce(this.fixedCost, (p1,p2)->p1+p2);
	}


	private long findNumberOfProducts(ShoppingCart cart) {
		return cart.getItems().stream().map(item->item.getProduct()).distinct().count();
	}


	private long findNumberOfDeliveries(ShoppingCart cart) {
		return cart.getItems().stream().map(item->item.getProduct()).map(product->product.getCategory()).distinct().count();
		
	}

}
