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

	private static final String HELP_MESSAGE = "Example : java -jar aggregator.jar file=c:\\tepm\\big_input.txt";

	public static void main(String[] args) {

		String inputPath = null;

		if (args.length == 0) {
			System.out.println(HELP_MESSAGE);
			System.exit(1);
		}
		for (String param : args) {
			String[] p = param.split("=");
			switch (p[0]) {
			case "file":
				inputPath = p[1];
				break;
			default:
				System.out.println(HELP_MESSAGE);
				System.exit(1);
			}
		}

		//inputPath = "c:\\tepm\\big_input.txt";

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
