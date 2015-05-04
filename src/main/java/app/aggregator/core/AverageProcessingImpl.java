package app.aggregator.core;

import app.aggregator.models.Instrument;

/**
 * Class impl of simple average for the INSTRUMENT1
 *  
 * @author Ogarkov Sergey
 *
 */
public class AverageProcessingImpl extends Processing {
	
	@Override
	public void add(Instrument instrument) {
		this.instruments.add(instrument);
	}

	@Override
	public float calculate() {
		float sum = 0; 		
		for(Instrument i : instruments) {
			sum += i.price;
		}
		return (sum/instruments.size());
	}
}
