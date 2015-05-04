package app.aggregator.core;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.text.ParseException;



import app.aggregator.launch.Running;
import app.aggregator.models.Instrument;

/**
 * Class read file with one INSTRUNET
 * 
 * @author Ogarkov Sergey
 *
 */
public class Reader implements Runnable {
	
	private String instruemntNumber;
	private Processing processing;

	
	public Reader(String instruemntNumber, Processing processing) {
		this.instruemntNumber = instruemntNumber;
		this.processing = processing;
	}
	
	public void aggregate(String instrument) throws ParseException {
		Instrument i = new Instrument(instrument);
		if(i.name.equals(this.instruemntNumber)) {		
			this.processing .add(i);
		}
	}	
	
	@Override
	public void run() {
		
		try {
			BufferedReader reader = Files.newBufferedReader(new File(Running.PATH).toPath(), 
					Charset.defaultCharset());
			String line = null;
			while ( (line = reader.readLine()) != null ) {
					try {
						aggregate(line);
					} catch (ParseException e) {
						e.printStackTrace();
					}
			}
			
			if(this.processing.size() > 0) {
				
				System.out.println(this.instruemntNumber+" = "+this.processing.calculate());
			} else {
				System.out.println(this.instruemntNumber+" = EMPTY");
			}	
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
