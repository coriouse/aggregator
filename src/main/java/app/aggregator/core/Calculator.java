package app.aggregator.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import app.aggregator.core.modul.AverageNewstInstrumentsModule;
import app.aggregator.model.Instrument;
import app.aggregator.util.DefinerInstrument;
/**
 * Calculator of instruments
 * 
 * @author Ogarkov Sergey
 *
 */
public class Calculator {
	/**
	 * List of instruments
	 */
	private static final Map<String, List<Instrument>> INSTRUMENTS = new HashMap<String, List<Instrument>>();
	/**
	 * List of modules
	 */
	private static final Map<String, EngineModule> MODULES = new HashMap<String, EngineModule>();

	private static final String INSTRUMENT1 = "INSTRUMENT1";
	private static final String INSTRUMENT2 = "INSTRUMENT2";
	private static final String INSTRUMENT3 = "INSTRUMENT3";

	private static final Integer INSTRUMENTS_COUNT = 10000;
	private static final Integer NEWST = 11;

	private String inputPath = null;

	static {
		init();
	}

	private static void init() {
		for (int i = 1; i < INSTRUMENTS_COUNT; i++) {
			INSTRUMENTS.put("INSTRUMENT" + i, new ArrayList<Instrument>());
		}
	}

	public Calculator(String inputPath) {
		this.inputPath = inputPath;
	}

	/**
	 * Add especially engine for some instrument 
	 * 
	 * @param EngineModule
	 * @return Calculator
	 */
	public Calculator addModule(EngineModule module) {
		module.addInstruments(INSTRUMENTS);
		addEngineModule(module);
		return this;
	}
	/**
	 * Add default module engins for reminding instruments
	 * 
	 * @return Calculator
	 */
	public Calculator addModuleDefault() {
		for (int i = 5; i < 10; i++) {
			String instrument = "INSTRUMENT" + i;
			if(!MODULES.containsKey(instrument)) {
				EngineModule module = new AverageNewstInstrumentsModule(instrument);
				module.addInstruments(INSTRUMENTS);
				addEngineModule(module);
			}
		}
		return this;
	}

	private void addEngineModule(EngineModule module) {
		MODULES.put(module.getInstrumentName(), module);
	}

	/**
	 * 
	 * Calculate result of instruments modules by Engins
	 * 
	 */
	public void calculate() {
		aggreagete();
		for (Entry<String, EngineModule> module : MODULES.entrySet()) {
			System.out.println(module.getKey() + ":" + module.getValue().calculate());
		}
	}

	private void aggreagete() {
		String line = null;
		try (BufferedReader reader = Files.newBufferedReader(new File(inputPath).toPath(), Charset.defaultCharset())) {
			while ((line = reader.readLine()) != null) {
				Instrument instrument = DefinerInstrument.defineOf(line);
				if (instrument != null) {
					add(instrument);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void add(Instrument instrument) {
		String name = instrument.getName();
		if (INSTRUMENTS.containsKey(name)) {
			if (INSTRUMENT1.equals(name) || INSTRUMENT2.equals(name)) {
				INSTRUMENTS.get(name).add(instrument);
			} else if (INSTRUMENT3.equals(name)) {
				INSTRUMENTS.get(INSTRUMENT3).add(instrument);
				MODULES.get(INSTRUMENT3).addInstruments(INSTRUMENTS);
			} else {
				if (INSTRUMENTS.get(name).size() == NEWST) {
					INSTRUMENTS.get(name).set(INSTRUMENTS.get(name).size() - 1, instrument);
					Collections.sort(INSTRUMENTS.get(name));
				} else {
					INSTRUMENTS.get(name).add(instrument);
				}
			}
		}
	}

}
