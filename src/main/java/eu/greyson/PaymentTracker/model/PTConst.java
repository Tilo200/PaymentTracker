package eu.greyson.PaymentTracker.model;

import org.springframework.stereotype.Component;

/***
 * Storing all constants & texts used in the application
 * 
 * @author Attila Bodis
 */
@Component
public class PTConst {

	public static final int SLEEP_TIME = 60 * 1000;

	public static final String QUIT = "quit";

	public static final String INPUT = "Input > ";

	public static final String SEPARATOR = "=================================================";

	public static final String HEADER = "Payment Tracker application - Author:Attila Bodis";

	public static final String SUCCEESS = "Successful";

	public static final String FILE_READ = "<<< Reading file: ";

	public static final String FILE_READ_ERROR = "An error occured during the payments file reading. Aborting import.\nusage: payment-tracker.jar <filename.extension>\n";

	public static final String VALIDATION_EXIT = "Exiting application.";

	public static final String VALIDATION_INVALID_INPUT = "Invalid input: ";

	public static final String VALIDATION_3_LETTERS = "Invalid input. Currency must be 3 letters !";

	public static final String VALIDATION_AMOUNT_NUMBER = "Invalid input. Amount must consit of only numbers!";
}
