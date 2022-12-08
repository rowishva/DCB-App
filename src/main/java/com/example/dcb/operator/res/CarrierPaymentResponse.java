package com.example.dcb.operator.res;

import com.example.dcb.operator.res.dto.CarrierPaymentResDTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CarrierPaymentResponse {

	CarrierPaymentResDTO transactionInfo;
}
