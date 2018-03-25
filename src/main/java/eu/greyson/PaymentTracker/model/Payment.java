package eu.greyson.PaymentTracker.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

/***
 * This class represents payment data model
 * 
 * @author Attila Bodis
 */
@Data
@AllArgsConstructor
public class Payment {

	private String currency;

	private BigDecimal amount;

	public Payment(String currency, String amount) {
		this.currency = currency;
		this.amount = new BigDecimal(amount);
	}

	@Override
	public String toString() {
		return currency + " " + amount;
	}
}
