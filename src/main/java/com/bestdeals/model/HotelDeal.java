package com.bestdeals.model;

import java.math.BigDecimal;

import com.bestdeals.util.Util;

public class HotelDeal implements Comparable<HotelDeal> {

	private String hotelName;
	private String nightlyRate;
	private String promoTxt;
	private String dealValue;
	private String dealType;
	private String startDate;
	private String endDate;
	private BigDecimal finalPrice;

	public HotelDeal() {
	}

	public HotelDeal(String[] personCsv) {
		this.setHotelName(personCsv[0]);
		this.setNightlyRate(personCsv[1]);
		this.setPromoTxt(personCsv[2]);
		this.setDealValue(personCsv[3]);
		this.setDealType(personCsv[4]);
		this.setStartDate(personCsv[5]);
		this.setEndDate(personCsv[6]);
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getNightlyRate() {
		return nightlyRate;
	}

	public void setNightlyRate(String nightlyRate) {
		this.nightlyRate = nightlyRate;
	}

	public String getPromoTxt() {
		return promoTxt;
	}

	public void setPromoTxt(String promoTxt) {
		this.promoTxt = promoTxt;
	}

	public String getDealValue() {
		return dealValue;
	}

	public void setDealValue(String dealValue) {
		this.dealValue = dealValue;
	}

	public String getDealType() {
		return dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(BigDecimal finalPrice) {
		this.finalPrice = finalPrice;
	}

	/**
	 * Customized ToString
	 */
	@Override
	public String toString() {
		return this.promoTxt;
	}

	/**
	 * Inform if is header file to ignore
	 */
	public boolean isFirstLine() {
		return this.getHotelName().equals("hotel_name");
	}

	public double calculatePercentage(double obtained, double total) {
		return obtained * 100 / total;
	}

	/**
	 * Calculate the final price after discount
	 */
	public void calculateFinalPrice(Input in) {

		DealTypeEnum type = DealTypeEnum.getEnum(dealType);

		switch (type) {
		case REBATE_3PLUS:
			this.finalPrice = calculateRebatePlus(this, in);
			break;
		case PCT:
			this.finalPrice = calculatePackage(this, in);
			break;
		case REBATE:
			this.finalPrice = calculateRebate(this, in);
			break;
		}
	}

	private BigDecimal calculateRebatePlus(HotelDeal hotelDeal, Input in) {

		BigDecimal nights = new BigDecimal(in.getNights());
		if (Integer.parseInt(in.getNights()) < 3) {
			return new BigDecimal(hotelDeal.getNightlyRate()).multiply(nights);
		} else {
			return new BigDecimal(hotelDeal.getNightlyRate()).multiply(nights).add(new BigDecimal(hotelDeal.getDealValue()));
		}
	}

	private BigDecimal calculatePackage(HotelDeal hotelDeal, Input in) {

		BigDecimal nights = new BigDecimal(in.getNights());
		final BigDecimal deal = new BigDecimal(hotelDeal.getDealValue());
		final BigDecimal rate = new BigDecimal(hotelDeal.getNightlyRate()).multiply(nights);
		final BigDecimal discount = Util.percentage(rate, deal);
		return rate.add(discount);
	}

	private BigDecimal calculateRebate(HotelDeal hotelDeal, Input in) {

		BigDecimal nights = new BigDecimal(in.getNights());
		return new BigDecimal(hotelDeal.getNightlyRate()).add(new BigDecimal(hotelDeal.getDealValue())).multiply(nights);
	}

	@Override
	public int compareTo(HotelDeal o) {
		return this.finalPrice.compareTo(o.finalPrice);
	}

	/**
	 * Check if hotel and interval maches
	 * 
	 * @param in
	 * @return
	 */
	public boolean match(Input in) {
		// Hotel diferent
		if (!this.getHotelName().equalsIgnoreCase(in.getHotel())) {
			return false;
		}
		// Valida range date
		if (!Util.isValidInterval(in.getDate(), this.getStartDate(), this.getEndDate())) {
			return false;
		}
		return true;
	}

}
