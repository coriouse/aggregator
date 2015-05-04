package app.aggregator.core;

import app.aggregator.models.Instrument;


/**
 * Class do processing of the calculation price INSTRUMENTS 
 * 
 * @author Ogarkov Sergey
 *
 */
public abstract class Processing {
	
	protected BoxInstruments instruments;
	
	public Processing() {
		this.instruments = new BoxInstruments();
	}	
	
	public int size() {
			return this.instruments.size();
	}
	
	protected abstract void add(Instrument instrument);
	
	protected abstract float calculate();

}
