package eu.greyson.PaymentTracker.input;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.greyson.PaymentTracker.model.PTConst;
import eu.greyson.PaymentTracker.model.Payment;
import eu.greyson.PaymentTracker.payment.PaymentService;

/***
 * @author Attila Bodis
 */
@Service
public class FileReaderService {

	@Autowired
	private PaymentService paymentService;

	/***
	 * Main file reader method for reading from file defined at application the startup <p>
	 * In case of any issue during file reading, the process will be abborted and skipped.
	 * 
	 * @param fileName
	 */
	public void readFile(String fileName) {
		System.out.println(PTConst.FILE_READ + fileName);
		BufferedReader br;

		try {
			br = new BufferedReader(new FileReader(fileName));
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				Payment payment = paymentService.parsePayment(sCurrentLine);
				if (payment != null) {
					paymentService.processPayment(payment);
				}
			}
			System.out.println(PTConst.SUCCEESS);
			System.out.println(PTConst.SEPARATOR);

		} catch (IOException e) {
			System.out.println(PTConst.FILE_READ_ERROR);
		}
	}
}
