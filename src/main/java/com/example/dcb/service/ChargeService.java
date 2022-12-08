package com.example.dcb.service;

import org.springframework.stereotype.Service;

import com.example.dcb.req.ChargingRequest;
import com.example.dcb.res.ChargingResponse;

@Service
public interface ChargeService {

	public ChargingResponse createCharge(ChargingRequest request);
}
