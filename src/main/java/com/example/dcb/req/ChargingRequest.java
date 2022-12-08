package com.example.dcb.req;

import com.example.dcb.req.dto.ChargingRequestDTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChargingRequest {

	ChargingRequestDTO chargingRequest;
}
