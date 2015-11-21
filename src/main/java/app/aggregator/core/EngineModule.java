package app.aggregator.core;

import java.util.List;
import java.util.Map;

import app.aggregator.model.Instrument;


/**
 * Class do processing of the calculation price INSTRUMENTS 
 * 
 * @author Ogarkov Sergey
 *
 */
public abstract class EngineModule {
	
	private Map<String, List<Instrument>> instruments;
	private String instrument;
	
	public EngineModule(String instrument) {
		this.instrument = instrument;
	}
	
	public void addInstruments(Map<String, List<Instrument>> instruments) {
		this.instruments = instruments;
	}

	
	protected String getInstrumentName() {
		return this.instrument;
	}
	
	public List<Instrument> getInstruments() {
		return instruments.get(this.instrument);
	}
	
	protected abstract Float calculate();
}
