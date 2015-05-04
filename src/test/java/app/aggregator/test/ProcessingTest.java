package app.aggregator.test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Test;

import app.aggregator.core.AverageProcessingImpl;
import app.aggregator.models.Instrument;

public class ProcessingTest {

	@Test
	public void averageProcessingTest() {
		InputStream is = ProcessingTest.class.getResourceAsStream("/input.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line;
		AverageProcessingImpl ap = new AverageProcessingImpl();
		try {
			while ((line = br.readLine()) != null) {
				Instrument instrunet = new Instrument(line);
				if("INSTRUMENT1".equals(instrunet.name)) {
					ap.add(instrunet);
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
		assertEquals(3, ap.size());
		assertTrue(9.0 == ap.calculate());
	}

}
