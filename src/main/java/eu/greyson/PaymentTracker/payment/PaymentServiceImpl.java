package eu.greyson.PaymentTracker.payment;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.greyson.PaymentTracker.model.Payment;
import eu.greyson.PaymentTracker.validator.Validate;

/***
 * @author Attila Bodis
 */
@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private Map<String, BigDecimal> paymentMap;

	@Autowired
	private Validate validate;

	@Override
	public Payment parsePayment(String payment) {
		if (validate.isValidInputFormat(payment)) {
			String[] paymentSplit = payment.split(" ");
			return new Payment(paymentSplit[0].toUpperCase(), paymentSplit[1]);
		}
		return null;
	}

	@Override
	public void processPayment(Payment payment) {
		String curenncy = payment.getCurrency();
		BigDecimal amount = paymentMap.get(payment.getCurrency());

		if (amount != null) {
			paymentMap.put(curenncy, amount.add(payment.getAmount()));
		} else {
			paymentMap.put(curenncy, payment.getAmount());
		}
	}

	@Override
	public String getNetCurrencies() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n\n");
		paymentMap.forEach((k, v) -> {
			if (v != BigDecimal.ZERO) {
				builder.append(k + " " + v + "\n");
			}
		});

		if (StringUtils.isBlank(builder.toString())) {
			return "";
		}

		return builder.toString();
	}
}
