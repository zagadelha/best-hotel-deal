package com.bestdeals.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.bestdeals.exception.AppException;
import com.bestdeals.model.Input;

public class Util {

	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("messages");
	public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

	/**
	 * Validate input data
	 * 
	 * @param args
	 * @return
	 */
	public static Input validate(String[] args) {

		Input in = new Input();

		// Check if has parameters
		if (args == null || args.length == 0 || args.length != 4) {
			throw new AppException("input.invalid.params", BUNDLE);
		}

		// Check if informed file is valid
		try {
			in.setDealsFile(new BufferedReader(new FileReader(args[0])));
		} catch (FileNotFoundException e) {
			throw new AppException("input.invalid.file", BUNDLE);
		}

		// Check if informed hotel name is valid
		if (args[1] != null && isString(args[1]) && !isDate(args[1])) {
			in.setHotel(args[1]);
		} else {
			throw new AppException("input.invalid.hotel", BUNDLE);
		}

		// Check if informed date is valid
		if (args[2] != null && isDate(args[2])) {
			in.setDate(args[2]);
		} else {
			throw new AppException("input.invalid.date", BUNDLE);
		}

		// Check if informed nights is valid
		if (args[3] != null && isNumber(args[3])) {
			in.setNights(args[3]);
		} else {
			throw new AppException("input.invalid.nights", BUNDLE);
		}

		return in;
	}

	public static boolean isString(Object o) {
		return o instanceof String;
	}

	public static boolean isDate(String date) {
		try {
			LocalDate.parse(date);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static boolean isNumber(String number) {
		if (number == null) {
			return false;
		}
		try {
			Double.parseDouble(number);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public static boolean isValidInterval(String intputDate, String startDate, String endDate) {

		LocalDate d1 = LocalDate.parse(intputDate);
		LocalDate d2 = LocalDate.parse(startDate);
		LocalDate d3 = LocalDate.parse(endDate);

		if (d1.isBefore(d2) || d1.isAfter(d3)) {
			return false;
		}
		return true;
	}

	public static BigDecimal percentage(BigDecimal base, BigDecimal pct) {
		return base.multiply(pct).divide(ONE_HUNDRED);
	}

}
