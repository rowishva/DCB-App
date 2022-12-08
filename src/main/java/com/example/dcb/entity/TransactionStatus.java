package com.example.dcb.entity;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum TransactionStatus {

	PENDING(0, "Pending"), SUCCESSFUL(1, "Successful"), FAILED(2, "Failed");

	private int code;

	private String name;

	private static final Map<Integer, TransactionStatus> LOOKUP = new HashMap<Integer, TransactionStatus>();

	static {
		for (TransactionStatus profileStatus : EnumSet.allOf(TransactionStatus.class)) {
			LOOKUP.put(profileStatus.getCode(), profileStatus);
		}
	}

	public static TransactionStatus fromCode(int code) {
		return LOOKUP.get(code);
	}
}
