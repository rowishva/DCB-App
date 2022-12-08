package com.example.dcb.config;

import java.math.BigDecimal;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.dcb.entity.Charge;
import com.example.dcb.operator.req.CarrierPaymentRequest;
import com.example.dcb.req.ChargingRequest;
import com.example.dcb.res.ChargingResponse;

@Configuration
public class ModelMapperConfig {

	private Logger LOGGER = LogManager.getLogger(this.getClass());

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		configProperty(modelMapper);
		configEnums(modelMapper);
		return modelMapper;
	}

	public void configProperty(ModelMapper modelMapper) {
		chargingRequestToCharge(modelMapper);
		chargeToChargingResponse(modelMapper);
		chargeToCarrierPaymentRequest(modelMapper);

	}

	public void configEnums(ModelMapper modelMapper) {

	}

	public void chargingRequestToCharge(ModelMapper modelMapper) {
		TypeMap<ChargingRequest, Charge> propertyMapper = modelMapper.createTypeMap(ChargingRequest.class,
				Charge.class);
		propertyMapper.addMappings(mapper -> {
			mapper.map(source -> source.getChargingRequest().getCustomerInfo().getMobileNo(), Charge::setMobileNo);
			mapper.map(source -> source.getChargingRequest().getTransactionInfo().getTransactionId(),
					Charge::setTransactionId);
			mapper.map(source -> source.getChargingRequest().getTransactionInfo().getItem(), Charge::setItem);
			mapper.map(source -> source.getChargingRequest().getTransactionInfo().getItemDescription(),
					Charge::setItemDescription);
			mapper.map(source -> source.getChargingRequest().getTransactionInfo().getBalanceType(),
					Charge::setBalanceType);
			mapper.map(source -> source.getChargingRequest().getTransactionInfo().getAmount(), Charge::setAmount);
			mapper.map(source -> source.getChargingRequest().getTransactionInfo().getCurrency(), Charge::setCurrency);
		});
		LOGGER.log(Level.INFO, "Convert -> ChargingRequest To Charge");
	}

	public void chargeToChargingResponse(ModelMapper modelMapper) {
		TypeMap<Charge, ChargingResponse> propertyMapper = modelMapper.createTypeMap(Charge.class,
				ChargingResponse.class);
		propertyMapper.addMappings(mapper -> {
			mapper.<String>map(source -> source.getMobileNo(),
					(dest, v) -> dest.getChargingResponse().getCustomerInfo().setMobileNo(v));
			mapper.<String>map(source -> source.getTransactionId(),
					(dest, v) -> dest.getChargingResponse().getTransactionInfo().setTransactionId(v));
			mapper.<String>map(source -> source.getItem(),
					(dest, v) -> dest.getChargingResponse().getTransactionInfo().setResponseStatus(v));
		});
		LOGGER.log(Level.INFO, "Convert -> Charge to ChargingResponse");
	}

	public void chargeToCarrierPaymentRequest(ModelMapper modelMapper) {
		TypeMap<Charge, CarrierPaymentRequest> propertyMapper = modelMapper.createTypeMap(Charge.class,
				CarrierPaymentRequest.class);
		propertyMapper.addMappings(mapper -> {
			mapper.<String>map(source -> source.getMobileNo(), (dest, v) -> dest.getTransactionInfo().setMsisdn(v));
			mapper.<String>map(source -> source.getTransactionId(), (dest, v) -> dest.getTransactionInfo().setRefId(v));
			mapper.<String>map(source -> source.getItem(), (dest, v) -> dest.getTransactionInfo().setItem(v));
			mapper.<String>map(source -> source.getItemDescription(),
					(dest, v) -> dest.getTransactionInfo().setItemDescription(v));
			mapper.<String>map(source -> source.getBalanceType(),
					(dest, v) -> dest.getTransactionInfo().setBalanceType(v));
			mapper.<BigDecimal>map(source -> source.getAmount(),
					(dest, v) -> dest.getTransactionInfo().setChargeAmount(v));
			mapper.<String>map(source -> source.getCurrency(), (dest, v) -> dest.getTransactionInfo().setCurrency(v));
		});
		LOGGER.log(Level.INFO, "Convert -> Charge to CarrierPaymentRequest");
	}

}
