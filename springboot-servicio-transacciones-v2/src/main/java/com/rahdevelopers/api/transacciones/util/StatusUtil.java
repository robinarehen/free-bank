package com.rahdevelopers.api.transacciones.util;

public enum StatusUtil {

	INVALID("INVALID"), PENDING("PENDING"), SETTLED("SETTLED"), FUTURE("FUTURE");

	private String value;

	StatusUtil(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
