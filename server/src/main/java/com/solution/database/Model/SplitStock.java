package com.solution.database.Model;

public class SplitStock {
	private  String date_traded;
	private String ticker_symbol;
	private float open_price;
	private float close_price;
	private float low_price;
	private float high_price;
	private float volume_traded;
	public String getDate_traded() {
		return date_traded;
	}
	public void setDate_traded(String date_traded) {
		this.date_traded = date_traded;
	}
	public String getTicker_symbol() {
		return ticker_symbol;
	}
	public void setTicker_symbol(String ticker_symbol) {
		this.ticker_symbol = ticker_symbol;
	}
	public float getOpen_price() {
		return open_price;
	}
	public void setOpen_price(float open_price) {
		this.open_price = open_price;
	}
	public float getClose_price() {
		return close_price;
	}
	public void setClose_price(float close_price) {
		this.close_price = close_price;
	}
	public float getLow_price() {
		return low_price;
	}
	public void setLow_price(float low_price) {
		this.low_price = low_price;
	}
	public float getHigh_price() {
		return high_price;
	}
	public void setHigh_price(float high_price) {
		this.high_price = high_price;
	}
	public float getVolume_traded() {
		return volume_traded;
	}
	public void setVolume_traded(float volume_traded) {
		this.volume_traded = volume_traded;
	}
	
	

}
