package com.example.dcb.service;

import org.springframework.stereotype.Service;

import com.example.dcb.operator.req.CarrierPaymentRequest;
import com.example.dcb.operator.res.CarrierPaymentResponse;
import com.example.dcb.res.dto.GenerateTokenResponse;

@Service
public interface OperatorBackendService {

	public GenerateTokenResponse generateToken();

	public CarrierPaymentResponse carrierPayment(CarrierPaymentRequest carrierPaymentRequest, String accessToken);
}
