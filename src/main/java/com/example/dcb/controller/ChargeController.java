package com.example.dcb.controller;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dcb.common.ServiceEndpoints;
import com.example.dcb.req.ChargingRequest;
import com.example.dcb.res.BaseResponse;
import com.example.dcb.res.ChargingResponse;
import com.example.dcb.service.ChargeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Charge")
@RestController
@RequestMapping(ServiceEndpoints.API_TRANSACTION_CHARGE)
public class ChargeController {

	private Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private ChargeService service;

	@ApiOperation(value = "Create Charge", response = BaseResponse.class)
	@PostMapping()
	public ChargingResponse createCharge(@RequestBody ChargingRequest request) {
		LOGGER.log(Level.INFO, "Calling ChargeController.createCharge()");
		return service.createCharge(request);
	}

}
