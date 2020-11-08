package com.tech.enthusiasts.products.search.svc.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public final class CommonUtil {
	
	private CommonUtil() throws IllegalAccessException {
		throw new IllegalAccessException("Cannot instantiate Utility class");
	}

	public static String getFormattedPrice(final BigDecimal productSellingPrice) {
		final Locale locale = new Locale("en", "IN");
		final NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
		return numberFormat.format(productSellingPrice.doubleValue());
	}
	
	public static String getFormattedPrice(final Double productSellingPrice) {
		final Locale locale = new Locale("en", "IN");
		final NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
		return numberFormat.format(productSellingPrice);
	}

	public static BigDecimal getPriceInDecimal(final Double sellingPrice) {
		return BigDecimal.valueOf(sellingPrice).setScale(2, RoundingMode.HALF_DOWN);
	}

}
