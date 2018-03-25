package eu.greyson.PaymentTracker.config;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 * PaymentConfig bean for storing the currency map
 * 
 * @author Attila Bodis
 */
@Configuration
public class PaymentConfig {

	private Map<String, BigDecimal> currencyMap = new HashMap<>();

	@Bean
	public synchronized Map<String, BigDecimal> currencyMapCache() {
		return currencyMap;
	}
}
