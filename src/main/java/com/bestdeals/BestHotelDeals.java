package com.bestdeals;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bestdeals.model.HotelDeal;
import com.bestdeals.model.Input;
import com.bestdeals.util.Util;

public class BestHotelDeals {

	public static void main(String[] args) {
		try {
			final Input in = Util.validate(args);
			searchDeal(in);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Search for deals
	 * 
	 * @param in
	 */
	private static void searchDeal(Input in) throws Exception {

		// Open csv file in memory
		List<HotelDeal> matchedHotelDeals = new ArrayList<>();
		String line = "";
		BufferedReader reader = in.getDealsFile();

		// Iterate throught all lines
		while ((line = reader.readLine()) != null) {
			HotelDeal deal = new HotelDeal(line.split(";"));
			if (deal.isFirstLine()) {
				continue;
			}
			// Check if each deal match with the input
			if (deal.match(in)) {
				matchedHotelDeals.add(deal);
				deal.calculateFinalPrice(in);
			}
		}

		// Order and get the best deal
		Collections.sort(matchedHotelDeals);

		// Print best deal
		if (matchedHotelDeals != null && matchedHotelDeals.size() > 0) {
			System.out.println(matchedHotelDeals.get(0).toString());
		} else {
			System.out.println("No deal available");
		}
	}

}
