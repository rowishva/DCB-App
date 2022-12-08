package com.example.dcb.res;

import com.example.dcb.res.dto.ChargingResponseDTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChargingResponse {

	ChargingResponseDTO chargingResponse;
}
