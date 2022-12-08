package com.example.dcb.service.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dcb.entity.Charge;
import com.example.dcb.entity.TransactionStatus;
import com.example.dcb.operator.req.CarrierPaymentRequest;
import com.example.dcb.operator.res.CarrierPaymentResponse;
import com.example.dcb.repository.ChargeRepository;
import com.example.dcb.req.ChargingRequest;
import com.example.dcb.res.ChargingResponse;
import com.example.dcb.res.dto.GenerateTokenResponse;
import com.example.dcb.service.ChargeService;
import com.example.dcb.service.OperatorBackendService;

@Service
public class ChargeServiceImpl implements ChargeService {

	private Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ChargeRepository chargeRepository;

	@Autowired
	private OperatorBackendService operatorBackendService;

	@Override
	public ChargingResponse createCharge(ChargingRequest request) {
		LOGGER.log(Level.INFO, "Calling ChargeServiceImpl.createCharge()");

		Charge charge = modelMapper.map(request, Charge.class);
		charge.setTransactionStatus(TransactionStatus.PENDING);
		charge = chargeRepository.save(charge);

		CarrierPaymentRequest carrierPaymentRequest = modelMapper.map(charge, CarrierPaymentRequest.class);
		GenerateTokenResponse generateTokenResponse = operatorBackendService.generateToken();
		CarrierPaymentResponse carrierPaymentResponse = operatorBackendService.carrierPayment(carrierPaymentRequest,
				generateTokenResponse.getAccess_token());
		String responseCode = carrierPaymentResponse.getTransactionInfo().getResponseCode();

		ChargingResponse chargingResponse = modelMapper.map(charge, ChargingResponse.class);
		chargingResponse.getChargingResponse().getTransactionInfo()
				.setResponseDesc(carrierPaymentResponse.getTransactionInfo().getResponseDesc());

		switch (responseCode) {
		case "00" -> {
			charge.setTransactionStatus(TransactionStatus.SUCCESSFUL);
			chargingResponse.getChargingResponse().getTransactionInfo()
					.setResponseStatus(TransactionStatus.SUCCESSFUL.name());
		}
		case "11" -> {
			charge.setTransactionStatus(TransactionStatus.FAILED);
			chargingResponse.getChargingResponse().getTransactionInfo()
					.setResponseStatus(TransactionStatus.FAILED.name());
		}
		}
		;
		chargeRepository.save(charge);
		return chargingResponse;
	}

}
