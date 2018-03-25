package eu.greyson.PaymentTracker.logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.greyson.PaymentTracker.model.PTConst;
import eu.greyson.PaymentTracker.payment.PaymentService;
import lombok.Getter;

/***
 * Main currency loging service, with the infinite loop for payment log output in fixed intervals <p>
 * The interval is defined in the {@link PTConst} class (default sleep time 60 sec.)
 * 
 * @author Attila Bodis
 */
@Service
public class LoggingService implements Runnable {

	@Getter
	private Thread loggerThread;

	@Autowired
	private PaymentService paymentService;

	@Override
	public void run() {
		while (true) {
			System.out.print(paymentService.getNetCurrencies());
			try {
				Thread.sleep(PTConst.SLEEP_TIME);
			} catch (InterruptedException e) {
				break;
			}
		}
	}

	public void start() {
		if (loggerThread == null) {
			loggerThread = new Thread(this, "LoggingService");
			loggerThread.start();
		}
	}
}
