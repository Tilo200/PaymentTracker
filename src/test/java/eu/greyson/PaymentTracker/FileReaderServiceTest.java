package eu.greyson.PaymentTracker;

import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import eu.greyson.PaymentTracker.input.FileReaderService;
import eu.greyson.PaymentTracker.payment.PaymentService;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class FileReaderServiceTest {

	@Autowired
	FileReaderService fileReaderService;

	@Autowired
	PaymentService paymentService;

	@Test
	public void fileInputValidTest() {
		fileReaderService.readFile("payments.txt");
		assertTrue("HKD 899\nRMB 3999\nUSD 899".equals(paymentService.getNetCurrencies().trim()));
	}

	@Test
	public void fileInputInvalidTest() {
		fileReaderService.readFile("payments");
		assertTrue(StringUtils.isBlank(paymentService.getNetCurrencies()));
	}

	@Configuration
	@ComponentScan("eu.greyson.PaymentTracker")
	public static class SpringConfig {

	}
}
