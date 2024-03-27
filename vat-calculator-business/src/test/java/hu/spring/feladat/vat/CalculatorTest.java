package hu.spring.feladat.vat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import hu.spring.feladat.openapi.model.CalculateResponse;

public class CalculatorTest {

	@Test
	public void testCalculatePrice_20() {
		Calculator calculator = new Calculator(20.0);
		
		CalculateResponse res = calculator.calculateFromPrice(100.0);
		
		Assertions.assertEquals(20.0, res.getVatrate());
		Assertions.assertEquals(100.0, res.getPrice());
		Assertions.assertEquals(20.0, res.getVat());
		Assertions.assertEquals(120.0, res.getFullprice());
	}
	
	@Test
	public void testCalculatePrice_13() {
		Calculator calculator = new Calculator(13.0);
		
		CalculateResponse res = calculator.calculateFromPrice(200.0);
		
		Assertions.assertEquals(13.0, res.getVatrate());
		Assertions.assertEquals(200.0, res.getPrice());
		Assertions.assertEquals(26.0, res.getVat());
		Assertions.assertEquals(226.0, res.getFullprice());
	}
	
	
	@Test
	public void testCalculateVat_13() {
		Calculator calculator = new Calculator(13.0);
		
		CalculateResponse res = calculator.calculateFromVat(26.0);
		
		Assertions.assertEquals(13.0, res.getVatrate());
		Assertions.assertEquals(200.0, res.getPrice());
		Assertions.assertEquals(26.0, res.getVat());
		Assertions.assertEquals(226.0, res.getFullprice());
	}
	
	@Test
	public void testCalculateFullprice_13() {
		Calculator calculator = new Calculator(13.0);
		
		CalculateResponse res = calculator.calculateFromFullprice(226.0);
		
		Assertions.assertEquals(13.0, res.getVatrate());
		Assertions.assertEquals(200.0, res.getPrice());
		Assertions.assertEquals(26.0, res.getVat());
		Assertions.assertEquals(226.0, res.getFullprice());
	}
}
