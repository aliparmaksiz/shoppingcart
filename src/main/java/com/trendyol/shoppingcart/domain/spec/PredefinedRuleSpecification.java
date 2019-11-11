package com.trendyol.shoppingcart.domain.spec;
import java.util.function.BiPredicate;

public abstract class PredefinedRuleSpecification<T,E> implements BiPredicate<T, E>{

	@Override
	public abstract boolean test(T input, E compare) ;

}
