package app.aggregator.models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import app.aggregator.utils.Constants;

@SuppressWarnings("rawtypes")
public class Instrument implements Comparable {
	
	public String name = "EMPTY";		
	public float price;	
	public Date date;
	public boolean isWorkDay;
			
	public Instrument(String instrument) {
		String[] arr = instrument.split("[,]");
		if(arr.length == 3) {
			Date date = getDate(arr[1]);
				this.name = arr[0];		
				this.date = date;
				this.price = Float.parseFloat(arr[2]);
				this.isWorkDay = isWorkDay(date); 
		}	
	}
	
	private static boolean isWorkDay(Date date) {
		Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
		return !Constants.WEEKEND.contains(calendar.get(Calendar.DAY_OF_WEEK));
	}
	
	
	private static Date getDate(String txtDate) {
		DateFormat  df = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
		Date date = null;
		try {
			date = df.parse(txtDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date; 
	}



	@Override
	public String toString() {
		return "Instrument [name=" + name + ", price=" + price + ", date="
				+ date + ", isWorkDay=" + isWorkDay + "]";
	}

	@Override
	public int compareTo(Object i) {
		Instrument instrument = (Instrument)i;
		if(this.date.getTime() == instrument.date.getTime()) {
			return 0;
		} else {
			return (this.date.getTime() < instrument.date.getTime() ? 1 : -1);	
		}
		
	}
	
	
	
}
