package app.aggregator.core.modul;

import java.util.Date;

import app.aggregator.core.EngineModule;
import app.aggregator.model.Instrument;

/**
 * Class impl of simple average of the INSTRUMENT2 for only Nov 2014
 * 
 * @author Ogarkov Sergey
 *
 */
public class AverageMonthModule extends EngineModule {

	private static final Integer YEAR = 2014;
	private static final Integer NOVEMBER = 10;

	private static final String INSTRUMENT = "INSTRUMENT2";

	public AverageMonthModule() {
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
			if (isNovember(i.getDate())) {
				sum += i.getPrice();
				counter++;
			}
		}
		return (sum / counter);
	}

	private boolean isNovember(Date data) {
		return (data.getMonth() == NOVEMBER && (data.getYear() + 1900) == YEAR);
	}

}
