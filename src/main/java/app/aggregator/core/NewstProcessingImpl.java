package app.aggregator.core;

import java.util.Collections;

import app.aggregator.models.Instrument;
import app.aggregator.utils.Constants;

/**
 * Class impl get sum of the  last 10 days 
 * 
 * @author Ogarkov Sergey
 *
 */
public class NewstProcessingImpl  extends Processing {


	public NewstProcessingImpl() {
		this.instruments = new BoxInstruments(Constants.NEWST);
	}	

	@SuppressWarnings("unchecked")
	@Override
	public void add(Instrument instrument) {
		if(this.instruments.size() == Constants.NEWST) {
			this.instruments.set(this.instruments.size()-1, instrument);
			Collections.sort(this.instruments);
		} else {
			this.instruments.add(instrument);
		}	
	}

	@Override
	public float calculate() {
		float sum = 0; 		
		for(Instrument i : instruments) {
			sum += i.price;
		}
		return (sum/instruments.size());
	}
	
	private void print() {
		for(Instrument i : this.instruments) {
			System.out.println(i);
		}
	}
}
