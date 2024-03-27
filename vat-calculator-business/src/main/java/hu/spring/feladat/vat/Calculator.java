package hu.spring.feladat.vat;

import hu.spring.feladat.openapi.model.CalculateResponse;

public class Calculator {

	private double vatrate;

	public Calculator(double vatrate) {
		super();
		this.vatrate = vatrate;
	}

	public CalculateResponse calculateFromPrice(double price) {

		double vat = price * (vatrate / 100);
		double fullprice = price + vat;
		return new CalculateResponse(price, vat, fullprice, vatrate);
	}

	public CalculateResponse calculateFromVat(double vat) {

		double price = vat / (vatrate / 100);
		double fullprice = price + vat;
		return new CalculateResponse(price, vat, fullprice, vatrate);
	}

	public CalculateResponse calculateFromFullprice(double fullprice) {

		double vat = Math.round(fullprice * (1 - (1 / (1 + (vatrate / 100)))));
		double price = fullprice - vat;
		return new CalculateResponse(price, vat, fullprice, vatrate);

	}
}
