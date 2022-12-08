package com.example.dcb.operator.res.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CarrierPaymentResDTO {

	String msisdn;
	String refId;
	String responseCode;
	String responseDesc;

}
