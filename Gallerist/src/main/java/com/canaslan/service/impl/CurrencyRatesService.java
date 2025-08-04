package com.canaslan.service.impl;


import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.canaslan.dto.CurrencyRatesResponse;
import com.canaslan.enums.MessageType;
import com.canaslan.exception.BaseException;
import com.canaslan.exception.ErrorMessage;
import com.canaslan.service.ICurrencyRatesService;

@Service
public class CurrencyRatesService implements ICurrencyRatesService{

	@Override
	public CurrencyRatesResponse getCurrencyRates(String startDate, String endDate) {

		String rootUrl = "https://evds2.tcmb.gov.tr/service/evds/";
		String series = "TP.DK.USD.A";
		String type = "json";
		
		String endpoint = rootUrl + "series=" +series + "&startDate=" + startDate + "&endDate=" + endDate + "&type=" + type;
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("key", "x8BiQd42fJ");
		
		HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
		
		RestTemplate restTemplate = new RestTemplate();
		
		
		try {
			ResponseEntity<CurrencyRatesResponse> response = restTemplate.exchange(endpoint, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<CurrencyRatesResponse>() {
			});
			if (response.getStatusCode().is2xxSuccessful()) {
				return response.getBody();
			}
		} catch (Exception e) {
			throw new BaseException(new ErrorMessage(e.getMessage(), MessageType.CURRENCY_RATES_IS_OCCURED));		
		}
		return null;
	}
}
