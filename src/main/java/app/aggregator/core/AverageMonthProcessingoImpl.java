package app.aggregator.core;

import app.aggregator.models.Instrument;
import app.aggregator.utils.Constants;

/**
 * Class impl of simple average of the INSTRUMENT2 for only Nov 2014 
 * 
 * @author Ogarkov Sergey
 *
 */
public class AverageMonthProcessingoImpl extends Processing {
	
	public AverageMonthProcessingoImpl() {
		this.instruments = new BoxInstruments();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void add(Instrument instrument) {
		if(instrument.date.getMonth() == Constants.NOVEMBER && (instrument.date.getYear()+1900) == Constants.YEAR) {
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

	
}
