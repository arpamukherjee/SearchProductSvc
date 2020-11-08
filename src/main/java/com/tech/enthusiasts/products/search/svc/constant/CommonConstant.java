package com.tech.enthusiasts.products.search.svc.constant;

public final class CommonConstant {

	private CommonConstant() throws IllegalAccessException {
		throw new IllegalAccessException("Cannot instantiate constant class");
	}

	public static final String STATUS_FAILURE = "ERROR";
	public static final String STATUS_FAILURE_DETAIL = "Facing Temporary Delay. Please try later";
	public static final String STATUS_FAILURE_CODE = "E001";
	
	public static final String STATUS_FAILURE_CODE_INVALID_IP = "E002";
	public static final String STATUS_FAILURE_INVALID_IP = "INVALID INPUT";
	
	public static final String SPACE_CHAR = " ";
	
}
