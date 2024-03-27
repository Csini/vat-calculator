package hu.spring.feladat.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import hu.spring.feladat.openapi.api.ApiApiDelegate;
import hu.spring.feladat.openapi.model.CalculateResponse;
import hu.spring.feladat.vat.Calculator;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class ApiDeledateController implements ApiApiDelegate {

	@Value("${allowedVatRates}")
	private Double[] allowedVatRates;

	@Override
	public ResponseEntity<CalculateResponse> calculateValues(Double vatrate, Double price, Double vat,
			Double fullprice) {

		List<Double> vatratesAsList = Arrays.asList(allowedVatRates);
		log.debug("allowedVatRates: " + vatratesAsList);

		if (!vatratesAsList.contains(vatrate)) {
			throw new IllegalArgumentException("Vatrate: " + vatrate + " is not in (" + vatratesAsList + ")");
		}

		if (Stream.of(price, vat, fullprice).filter(x -> x != null).count() != 1) {
			throw new IllegalArgumentException(
					"Price, vat, fullprice -> only one can be and must be in the input, the same time!");
		}

		Calculator calculator = new Calculator(vatrate);

		if (price != null) {
			return ResponseEntity.ok(calculator.calculateFromPrice(price));
		} else if (vat != null) {
			return ResponseEntity.ok(calculator.calculateFromVat(vat));
		} else if (fullprice != null) {
			return ResponseEntity.ok(calculator.calculateFromFullprice(fullprice));
		}
		return ApiApiDelegate.super.calculateValues(vatrate, price, vat, fullprice);
	}
}
