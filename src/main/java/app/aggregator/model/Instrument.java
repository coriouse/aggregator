package app.aggregator.model;

import java.util.Date;

public class Instrument implements Comparable<Instrument> {

	private String name;
	private float price;
	private Date date;

	public Instrument(String name, float price, Date date) {
		super();
		this.name = name;
		this.price = price;
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Instrument [name=" + name + ", price=" + price + ", date=" + date + "]";
	}

	@Override
	public int compareTo(Instrument i) {
		Instrument instrument = i;
		if (this.date.getTime() == instrument.date.getTime()) {
			return 0;
		} else {
			return (this.date.getTime() < instrument.date.getTime() ? 1 : -1);
		}
	}

}
