package com.example.dcb.config;

import org.modelmapper.PropertyMap;

import com.example.dcb.entity.Charge;
import com.example.dcb.req.ChargingRequest;

public final class ModelMapPropertyMap {

	public static PropertyMap<ChargingRequest, Charge> chargingRequestToCharge() {
		return new PropertyMap<ChargingRequest, Charge>() {
			protected void configure() {
				// map().setMobileNo(source.getChargingRequest().getCustomerInfo().getMobileNo());

			}
		};
	}
}
