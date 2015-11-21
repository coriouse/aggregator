package app.aggregator.core.modul;

import app.aggregator.core.EngineModule;
import app.aggregator.model.Instrument;

/**
 * Class impl get sum of the last 10 days
 * 
 * @author Ogarkov Sergey
 *
 */
public class AverageNewstInstrumentsModule extends EngineModule {

	public AverageNewstInstrumentsModule(String instrument) {
		super(instrument);
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
