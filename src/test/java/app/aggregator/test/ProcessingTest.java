package app.aggregator.test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import app.aggregator.core.EngineModule;
import app.aggregator.core.modul.AverageModule;
import app.aggregator.model.Instrument;
import app.aggregator.util.DefinerInstrument;

public class ProcessingTest {

	private static final Map<String, List<Instrument>> INSTRUMENTS = new HashMap<String, List<Instrument>>();

	private static final String INSTRUMENT = "INSTRUMENT1";

	@Test
	public void averageProcessingTest() {

		INSTRUMENTS.put(INSTRUMENT, new ArrayList<Instrument>());

		InputStream is = ProcessingTest.class.getResourceAsStream("/input.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line;
		AverageModule averageModule = new AverageModule();

		try {
			while ((line = br.readLine()) != null) {
				Instrument instrunet = DefinerInstrument.defineOf(line);
				if (instrunet != null) {
					if (INSTRUMENT.equals(instrunet.getName())) {
						INSTRUMENTS.get(INSTRUMENT).add(instrunet);

					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		averageModule.addInstruments(INSTRUMENTS);
		assertEquals(3, INSTRUMENTS.get(INSTRUMENT).size());
		assertTrue(9.0 == averageModule.calculate());
	}

}
