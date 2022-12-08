package com.example.dcb.res.dto;

import com.example.dcb.req.dto.CustomerInfoDTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChargingResponseDTO {

	CustomerInfoDTO customerInfo;
	TransactionInfoResDTO transactionInfo;

}
