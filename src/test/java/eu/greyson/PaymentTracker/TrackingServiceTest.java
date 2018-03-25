package eu.greyson.PaymentTracker;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import eu.greyson.PaymentTracker.input.ConsoleReaderService;
import eu.greyson.PaymentTracker.input.FileReaderService;
import eu.greyson.PaymentTracker.logger.LoggingService;
import eu.greyson.PaymentTracker.payment.PaymentService;
import eu.greyson.PaymentTracker.payment.PaymentServiceImpl;
import eu.greyson.PaymentTracker.tracker.TrackingService;
import eu.greyson.PaymentTracker.validator.Validate;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class TrackingServiceTest {

	@InjectMocks
	private TrackingService trackingService;

	@Mock
	private ConsoleReaderService consoleService;

	@Mock
	private FileReaderService fileService;

	@Mock
	private LoggingService loggingService;

	@Spy
	private Validate validate = new Validate();

	@Spy
	private Map<String, BigDecimal> paymentMap = new HashMap<>();

	@Spy
	@InjectMocks
	private PaymentService paymentService = new PaymentServiceImpl();

	@Test
	public void TrackingServiceSuccessTest() {

		String input = "USD 100";

		Mockito.when(consoleService.isReading()).thenReturn(true, false);
		Mockito.when(consoleService.readInput(any())).thenReturn(input);
		Mockito.when(loggingService.getLoggerThread()).thenReturn(new Thread());

		trackingService.run();

		assertTrue(input.equals(paymentService.getNetCurrencies().trim()));
	}

	@Configuration
	@ComponentScan("eu.greyson.PaymentTracker")
	public static class SpringConfig {
	}
}
