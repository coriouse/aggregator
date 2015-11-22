package app.aggregator.core.modul;

import app.aggregator.core.EngineModule;
import app.aggregator.model.Instrument;

public class OnFlyModule extends EngineModule {

	private Float result;

	private static final String INSTRUMENT = "INSTRUMENT3";

	public OnFlyModule() {
		super(INSTRUMENT);
	}

	@Override
	public Float calculate() {
		return result;
	}

	public void refresh() {
		float sum = 0;
		int counter = 0;
		for (Instrument i : getInstruments()) {
			sum += i.getPrice();
			counter++;
		}
		result = (sum / counter);
	}

}
