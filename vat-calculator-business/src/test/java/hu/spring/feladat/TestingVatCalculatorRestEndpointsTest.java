package hu.spring.feladat;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class TestingVatCalculatorRestEndpointsTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void shouldReturnDefaultMessageWithoutQueryparams() throws Exception {
		this.mockMvc.perform(get("/vat-calculator-business/v1/api/calculate/20")).andDo(print())
				.andExpect(status().isBadRequest());
	}

	@Test
	void shouldReturnDefaultMessageWithToMuchQueryparams() throws Exception {
		this.mockMvc.perform(get("/vat-calculator-business/v1/api/calculate/{vatrate}", 20).queryParam("price", "100")
				.queryParam("vat", "25")).andDo(print()).andExpect(status().isBadRequest());
	}

	@Test
	void shouldReturnDefaultMessageWith_not_allowed_vatrate() throws Exception {
		this.mockMvc.perform(get("/vat-calculator-business/v1/api/calculate/{vatrate}", 30).queryParam("price", "100")
				.queryParam("vat", "25")).andDo(print()).andExpect(status().isBadRequest());
	}

	@Test
	void shouldReturnDefaultMessageWith_negative_Queryparams_price() throws Exception {
		this.mockMvc.perform(get("/vat-calculator-business/v1/api/calculate/{vatrate}", 20).queryParam("price", "-100"))
				.andDo(print()).andExpect(status().is5xxServerError());
	}

	@Test
	void shouldReturnDefaultMessageWith_negative_Queryparams_vat() throws Exception {
		this.mockMvc.perform(get("/vat-calculator-business/v1/api/calculate/{vatrate}", 20).queryParam("vat", "-13"))
				.andDo(print()).andExpect(status().is5xxServerError());
	}

	@Test
	void shouldReturnDefaultMessageWith_negative_Queryparams_fullprice() throws Exception {
		this.mockMvc
				.perform(get("/vat-calculator-business/v1/api/calculate/{vatrate}", 20).queryParam("fullprice", "-165"))
				.andDo(print()).andExpect(status().is5xxServerError());
	}

	@Test
	void shouldReturnDefaultMessageWith_ok_Queryparams_price() throws Exception {
		this.mockMvc.perform(get("/vat-calculator-business/v1/api/calculate/{vatrate}", 20).queryParam("price", "100"))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	void shouldReturnDefaultMessageWith_ok_Queryparams_vat() throws Exception {
		this.mockMvc.perform(get("/vat-calculator-business/v1/api/calculate/{vatrate}", 20).queryParam("vat", "20"))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	void shouldReturnDefaultMessageWith_ok_Queryparams_fullprice() throws Exception {
		this.mockMvc
				.perform(get("/vat-calculator-business/v1/api/calculate/{vatrate}", 20).queryParam("fullprice", "150"))
				.andDo(print()).andExpect(status().isOk());
	}
}