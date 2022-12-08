package com.example.dcb.operator.req.dto;

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
public class CarrierPaymentDTO {

	String msisdn;
	String refId;
	String item;
	String itemDescription;
	String balanceType;
	BigDecimal chargeAmount;
	String currency;
}
