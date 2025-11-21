package com.itc475.RewardsProject.mapper;

import java.math.BigDecimal;

public class RewardsPreferenceView {
	
	private String stand_id;
	private int available_seats;
	private int preferred_seats;
	private BigDecimal discount_price;
	private BigDecimal estimatedTotalEarnings;
	
	public String getStand_id() {
		return stand_id;
	}
	public void setStand_id(String stand_id) {
		this.stand_id = stand_id;
	}
	public int getAvailable_seats() {
		return available_seats;
	}
	public void setAvailable_seats(int available_seats) {
		this.available_seats = available_seats;
	}
	public int getPreferred_seats() {
		return preferred_seats;
	}
	public void setPreferred_seats(int preferred_seats) {
		this.preferred_seats = preferred_seats;
	}
	public BigDecimal getDiscount_price() {
		return discount_price;
	}
	public void setDiscount_price(BigDecimal discount_price) {
		this.discount_price = discount_price;
	}
	public BigDecimal getEstimatedTotalEarnings() {
		return estimatedTotalEarnings;
	}
	public void setEstimatedTotalEarnings(BigDecimal estimatedTotalEarnings) {
		this.estimatedTotalEarnings = estimatedTotalEarnings;
	}

}
