package app.aggregator.launch;

import app.aggregator.core.Calculator;
import app.aggregator.model.Instrument;

import app.aggregator.core.EngineModule;
import app.aggregator.core.modul.AverageModule;
import app.aggregator.core.modul.AverageMonthModule;
import app.aggregator.core.modul.OnFlyModule;

/**
 * Class entry point in programm
 * 
 * @author Ogarkov Sergey
 *
 */
public class Launcher {

	public static void main(String[] args) {

		// String inputPath="c:\\temp\\small_input.txt";
		String inputPath = "c:\\temp\\big_input.txt";

		Calculator calculator = new Calculator(inputPath);
		calculator.addModule(new AverageModule()).addModule(new AverageMonthModule()).addModule(new OnFlyModule())
				.addModule(new EngineModule("INSTRUMENT4") {

					@Override
					public Float calculate() {
						float sum = 0;
						int counter = 0;
						for (Instrument i : getInstruments()) {
							sum += i.getPrice();
							counter++;
						}
						return (sum / counter);
					}
				}).addModuleDefault().calculate();

	}
}
