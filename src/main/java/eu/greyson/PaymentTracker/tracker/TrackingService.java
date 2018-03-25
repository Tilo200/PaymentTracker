package eu.greyson.PaymentTracker.tracker;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.greyson.PaymentTracker.input.ConsoleReaderService;
import eu.greyson.PaymentTracker.input.FileReaderService;
import eu.greyson.PaymentTracker.logger.LoggingService;
import eu.greyson.PaymentTracker.model.PTConst;
import eu.greyson.PaymentTracker.model.Payment;
import eu.greyson.PaymentTracker.payment.PaymentService;
import lombok.Getter;
import lombok.Setter;

/***
 * Main Payment tracker service with the infinite loop for reading keyboard input
 * 
 * @author Attila Bodis
 */
@Service
public class TrackingService implements Runnable {

	@Autowired
	private FileReaderService fileService;

	@Autowired
	private ConsoleReaderService consoleService;

	@Autowired
	private LoggingService loggingService;

	@Autowired
	private PaymentService paymentService;

	@Getter
	private Thread trackerThread;

	@Setter
	private String filename;

	@Override
	public void run() {
		System.out.println(PTConst.SEPARATOR + "\n" + PTConst.HEADER + "\n" + PTConst.SEPARATOR);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		// fileName argument check, if true try to read file
		if (StringUtils.isNoneBlank(filename)) {
			fileService.readFile(filename);
		}

		while (consoleService.isReading()) {
			String input = consoleService.readInput(bufferedReader);
			Payment payment = paymentService.parsePayment(input);

			if (payment != null) {
				paymentService.processPayment(payment);
			}
		}
		// before application close, thread interuption (to not wait for the
		// sleep duration of logger)
		loggingService.getLoggerThread().interrupt();
		System.out.println(PTConst.SEPARATOR);
	}

	public void start() {
		if (trackerThread == null) {
			trackerThread = new Thread(this, "TrackerService");
			trackerThread.start();
		}
	}
}
