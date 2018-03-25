package eu.greyson.PaymentTracker;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import eu.greyson.PaymentTracker.payment.PaymentServiceImpl;
import eu.greyson.PaymentTracker.validator.Validate;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class PaymentServiceTest {

	@InjectMocks
	private PaymentServiceImpl paymentService;

	@Spy
	private Map<String, BigDecimal> paymentMap = new HashMap<>();

	@Spy
	private Validate validate = new Validate();

	@Test
	public void successfulPaymentValidationTest() {
		String payment = "USD 123";

		assertTrue("USD".equals(paymentService.parsePayment(payment).getCurrency()));
		assertTrue("123".equals(paymentService.parsePayment(payment).getAmount().toString()));
	}

	@Test
	public void invalidAmountValidationTest() {
		String payment = "USD ABC123";
		assertNull(paymentService.parsePayment(payment));
	}

	@Test
	public void invalidPaymentValidationTest() {
		String payment = "USD123";
		assertNull(paymentService.parsePayment(payment));
	}

	@Test
	public void invalidCurrencyValidationTest() {
		String payment = "US 123";
		assertNull(paymentService.parsePayment(payment));
	}

	@Test
	public void quitValidationTest() {
		String payment = "quit";
		assertNull(paymentService.parsePayment(payment));
	}

	@Configuration
	@ComponentScan("eu.greyson.PaymentTracker")
	public static class SpringConfig {
	}
}
