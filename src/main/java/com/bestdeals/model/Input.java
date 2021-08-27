package com.bestdeals.model;

import java.io.BufferedReader;

public class Input {

	private BufferedReader dealsFile;
	private String hotel;
	private String date;
	private String nights;

	public BufferedReader getDealsFile() {
		return dealsFile;
	}

	public void setDealsFile(BufferedReader dealsFile) {
		this.dealsFile = dealsFile;
	}

	public String getHotel() {
		return hotel;
	}

	public void setHotel(String hotel) {
		this.hotel = hotel;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNights() {
		return nights;
	}

	public void setNights(String nights) {
		this.nights = nights;
	}

}
