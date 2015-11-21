package app.aggregator.core.modul;

import app.aggregator.core.EngineModule;
import app.aggregator.model.Instrument;

/**
 * Class impl of simple average for the INSTRUMENT1
 * 
 * @author Ogarkov Sergey
 *
 */
public class AverageModule extends EngineModule {

	private static final String INSTRUMENT = "INSTRUMENT1";

	public AverageModule() {
		super(INSTRUMENT);
	}

	@Override
	public Float calculate() {
		return getAverage();
	}

	private Float getAverage() {
		float sum = 0;
		int counter = 0;
		for (Instrument i : getInstruments()) {
			sum += i.getPrice();
			counter++;
		}
		return (sum / counter);
	}

}
