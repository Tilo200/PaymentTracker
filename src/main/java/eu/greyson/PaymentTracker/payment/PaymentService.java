package eu.greyson.PaymentTracker.payment;

import eu.greyson.PaymentTracker.model.Payment;

/***
 * PaymentService Interface
 * 
 * @author Attila Bodis
 */
public interface PaymentService {

	/***
	 * Payment parsing method with validations for input data
	 *   
	 * @param payment String representation of the payment (e.g. input from keyboard)
	 * @return {@link Payment} object with filled currency & amount attributes,<p> <b>null</b> if invalid payment inserted
	 */
	Payment parsePayment(String payment);

	/***
	 * Main payment processing method, searches if a currency is already in memory and updates accordingly
	 * @param payment 
	 */
	void processPayment(Payment payment);

	/***
	 * Method for getting the InMemory currencies 
	 * @return String representations of the actual InMemory payments
	 */
	String getNetCurrencies();
}
