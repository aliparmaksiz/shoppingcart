package com.trendyol.shoppingcart.domain.delivery;

import java.util.function.BiFunction;

public interface DeliveryCalculator extends BiFunction<Double, Integer, Double> {

}
