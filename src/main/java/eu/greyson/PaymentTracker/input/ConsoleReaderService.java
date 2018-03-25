package eu.greyson.PaymentTracker.input;

import java.io.BufferedReader;

import org.springframework.stereotype.Service;

import eu.greyson.PaymentTracker.model.PTConst;
import lombok.Getter;

/***
 * @author Attila Bodis
 */
@Service
public class ConsoleReaderService {

	@Getter
	boolean reading = true;

	/***
	 * Main console reader method for reading keyboard input and checking for the "quit" escape string
	 * 
	 * @param bufferedReader reader for the keyboard inputStream
	 * @return keyboard input as a String, return null if encounters any bufferedReader issue
	 */
	public String readInput(BufferedReader bufferedReader) {
		System.out.print(PTConst.INPUT);
		String inputLine = null;

		try {
			inputLine = bufferedReader.readLine();

			if (PTConst.QUIT.equals(inputLine.toLowerCase())) {
				reading = false;
				return PTConst.QUIT;
			}
			return inputLine;

		} catch (Exception e) {
			System.out.println(PTConst.VALIDATION_INVALID_INPUT + inputLine);
			return null;
		}
	}
}
