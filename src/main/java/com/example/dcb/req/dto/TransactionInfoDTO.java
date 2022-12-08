package com.example.dcb.req.dto;

import java.math.BigDecimal;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionInfoDTO {

	String transactionId;
	String item;
	String itemDescription;
	String balanceType;
	BigDecimal amount;
	String currency;
}
