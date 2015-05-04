package app.aggregator.core;

import java.util.ArrayList;

import app.aggregator.models.Instrument;
/**
 * Store for INSTRUMENTS
 * 
 * @author Ogarkov Sergey
 *
 */
public class BoxInstruments extends ArrayList<Instrument> {
	
	private static final long serialVersionUID = 1L;
		
	public BoxInstruments(int i) {
		super(i);
	}

	public BoxInstruments() {
		super();
	}
	
	public boolean add(Instrument instrument) {
		boolean isOk = false;
		//if it work day
		if(instrument.isWorkDay) {
			isOk = super.add(instrument);
		}
		return isOk;
	}
}
