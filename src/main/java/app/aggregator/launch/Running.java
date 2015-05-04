package app.aggregator.launch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import app.aggregator.core.AverageMonthProcessingoImpl;
import app.aggregator.core.AverageProcessingImpl;
import app.aggregator.core.NewstProcessingImpl;
import app.aggregator.core.Processing;
import app.aggregator.core.Reader;
import app.aggregator.models.Instrument;
import app.aggregator.utils.Constants;
/**
 * Class entry point in programm
 * 
 *@author Ogarkov Sergey 
 *
 */
public class Running {
	
	public final static String PATH="c:\\temp\\big_input.txt";
	
	public static void main(String[] args) {
		
		ExecutorService executor = Executors.newFixedThreadPool(Constants.THREADS);
			//For INSTRUMENT1 – mean
			executor.execute(new Reader("INSTRUMENT1", new AverageProcessingImpl()));
			//For INSTRUMENT2 – mean for November 2014
			executor.execute(new Reader("INSTRUMENT2", new AverageMonthProcessingoImpl()));
				
			//For INSTRUMENT3 – any other statistical calculation that we can compute "on-the-fly" as we read the file (it's up to you)			
			executor.execute(new Reader("INSTRUMENT3", new Processing() {
				
					@Override
					public void add(Instrument instrument) {
						if(instrument.price > 30) 
							this.instruments.add(instrument);
					}
					@Override
					public float calculate() {
						float d = 0; 		
						for(Instrument i : instruments) {
							d += i.price;
						}
						return (d/instruments.size());
					}
				}));
			
			//For any other instrument from the input file - sum of the newest 10 elements
			for(int i = 4;i<Constants.INSTRUMENTS;i++) {
				executor.execute(new Reader("INSTRUMENT"+i,  new NewstProcessingImpl()));
			}
			
			executor.shutdown();	
		}
}
