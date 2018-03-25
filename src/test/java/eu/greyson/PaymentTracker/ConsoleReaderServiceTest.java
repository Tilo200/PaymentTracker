package eu.greyson.PaymentTracker;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.StringReader;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import eu.greyson.PaymentTracker.input.ConsoleReaderService;
import eu.greyson.PaymentTracker.model.PTConst;

@ContextConfiguration(classes = ConsoleReaderService.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ConsoleReaderServiceTest {

	@Autowired
	ConsoleReaderService consoleReaderService;

	@Test
	public void consoleInputValidTest() {
		String fakeInput = "USD 123";
		String resutl = consoleReaderService.readInput(new BufferedReader(createReader(fakeInput)));

		assertTrue(fakeInput.equals(resutl));
	}

	@Test
	public void consoleInputQuitTest() {
		String fakeInput = "quit";
		String resutl = consoleReaderService.readInput(new BufferedReader(createReader(fakeInput)));

		assertTrue(PTConst.QUIT.equals(resutl));
		assertFalse(consoleReaderService.isReading());
	}

	@Test
	public void consoleInputInvalidTest() {
		assertNull(consoleReaderService.readInput(null));
	}

	private StringReader createReader(String input) {
		return new StringReader(input);
	}
}
