package com.example.dcb.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.dcb.common.ApplicationConstants;
import com.example.dcb.common.ServiceEndpoints;
import com.example.dcb.operator.req.CarrierPaymentRequest;
import com.example.dcb.operator.res.CarrierPaymentResponse;
import com.example.dcb.res.dto.GenerateTokenResponse;
import com.example.dcb.service.OperatorBackendService;

@Service
public class OperatorBackendServiceImpl implements OperatorBackendService {

	private Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private RestTemplate restTemplate;

	@Value("${operator.backend.service}")
	private String operatorBackendServiceUrl;

	@Value("${operator.service.key}")
	private String operatorServiceKey;

	@Value("${operator.service.name}")
	private String operatorServiceName;

	@Override
	public GenerateTokenResponse generateToken() {
		LOGGER.log(Level.INFO, "Calling OperatorBackendServiceImpl.generateToken()");
		String urlTemplate = UriComponentsBuilder
				.fromHttpUrl(operatorBackendServiceUrl + ServiceEndpoints.GENERATE_TOKEN)
				.queryParam(ApplicationConstants.SERVICE_KEY, "{service_key}")
				.queryParam(ApplicationConstants.SERVICE_NAME, "{service_name}").encode().toUriString();

		Map<String, String> params = new HashMap<>();
		params.put(ApplicationConstants.SERVICE_KEY, operatorServiceKey);
		params.put(ApplicationConstants.SERVICE_NAME, operatorServiceName);

		ResponseEntity<GenerateTokenResponse> response = restTemplate.getForEntity(urlTemplate,
				GenerateTokenResponse.class, params);
		return response.getBody();
	}

	@Override
	public CarrierPaymentResponse carrierPayment(CarrierPaymentRequest carrierPaymentRequest, String accessToken) {
		LOGGER.log(Level.INFO, "Calling OperatorBackendServiceImpl.carrierPayment()");
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		headers.setBearerAuth(accessToken);

		String urlTemplate = UriComponentsBuilder
				.fromHttpUrl(operatorBackendServiceUrl + ServiceEndpoints.CARRIER_PAYMENT).encode().toUriString();
		HttpEntity<?> request = new HttpEntity<>(carrierPaymentRequest, headers);

		ResponseEntity<CarrierPaymentResponse> response = restTemplate.postForEntity(urlTemplate, request,
				CarrierPaymentResponse.class);

		return response.getBody();
	}

}
