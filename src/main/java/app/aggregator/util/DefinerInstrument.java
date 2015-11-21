package app.aggregator.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import app.aggregator.model.Instrument;

public class DefinerInstrument {

	public static final List<Integer> WEEKEND = new ArrayList<Integer>() {
		/**
		* 
		*/
		private static final long serialVersionUID = 1L;

		{
			add(1);
			add(7);
		}
	};

	public static Instrument defineOf(String line) {
		Instrument instrument = null;
		String[] arr = line.split("[,]");
		if (arr.length == 3) {
			Date date = getDate(arr[1]);
			if (isWorkDay(date)) {
				String name = arr[0];
				Float price = Float.parseFloat(arr[2]);
				instrument = new Instrument(name, price, date);
			}
		}
		return instrument;

	}

	private static boolean isWorkDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return !WEEKEND.contains(calendar.get(Calendar.DAY_OF_WEEK));
	}

	private static Date getDate(String txtDate) {
		DateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
		Date date = null;
		try {
			date = df.parse(txtDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
