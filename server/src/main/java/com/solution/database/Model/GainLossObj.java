package com.solution.database.Model;

public class GainLossObj {

    private String symbol;
    private int year;
    private int month;
    private double mav10;
    private double mav30;
    private double gl_for_month_per_share;
    private int lot_size;
    private double gl_for_lot_size;
    private String signal;

    public GainLossObj(String symbol, int year, int month, double mav10, double mav30, double gl_for_month_per_share, int lot_size, double gl_for_lot_size, String signal) {
        this.symbol = symbol;
        this.year = year;
        this.month = month;
        this.mav10 = mav10;
        this.mav30 = mav30;
        this.gl_for_month_per_share = gl_for_month_per_share;
        this.lot_size = lot_size;
        this.gl_for_lot_size = gl_for_lot_size;
        this.signal = signal;
    }

    public GainLossObj() {
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double getMav10() {
        return mav10;
    }

    public void setMav10(double mav10) {
        this.mav10 = mav10;
    }

    public double getMav30() {
        return mav30;
    }

    public void setMav30(double mav30) {
        this.mav30 = mav30;
    }

    public double getGl_for_month_per_share() {
        return gl_for_month_per_share;
    }

    public void setGl_for_month_per_share(double gl_for_month_per_share) {
        this.gl_for_month_per_share = gl_for_month_per_share;
    }

    public int getLot_size() {
        return lot_size;
    }

    public void setLot_size(int lot_size) {
        this.lot_size = lot_size;
    }

    public double getGl_for_lot_size() {
        return gl_for_lot_size;
    }

    public void setGl_for_lot_size(double gl_for_lot_size) {
        this.gl_for_lot_size = gl_for_lot_size;
    }

    public String getSignal() {
        return signal;
    }

    public void setSignal(String signal) {
        this.signal = signal;
    }

}
