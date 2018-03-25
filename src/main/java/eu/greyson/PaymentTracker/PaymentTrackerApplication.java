package eu.greyson.PaymentTracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.SimpleCommandLinePropertySource;

import eu.greyson.PaymentTracker.logger.LoggingService;
import eu.greyson.PaymentTracker.tracker.TrackingService;

/***
 * Main SpringBoot class of Payment-Tracker application.
 * 
 * @author Attila Bodis
 *
 */
@SpringBootApplication
public class PaymentTrackerApplication implements CommandLineRunner {

	@Autowired
	private LoggingService loggingService;

	@Autowired
	private TrackingService trackerService;

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(PaymentTrackerApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.setLogStartupInfo(false);
		app.run(args);
	}

	@Override
	public void run(String... args) {
		SimpleCommandLinePropertySource sclps = new SimpleCommandLinePropertySource(args);
		trackerService.setFilename(sclps.getProperty("fileName"));

		loggingService.start();
		trackerService.start();
	}
}
