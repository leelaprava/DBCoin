package com.solution.database.Model;

public class MonthwiseAvgClosePriceofaYearObj {

    private int month;
    private int year;
    private float avgClosePrice;
    

    public MonthwiseAvgClosePriceofaYearObj() {

    }

    public MonthwiseAvgClosePriceofaYearObj(int month, int year, float avgClosePrice) {
        this.month = month;
        this.year = year;
        this.avgClosePrice = avgClosePrice;
    }


    @Override
    public String toString() {
        return "MonthwiseAvgClosePriceofaYearObj{" +
                "month=" + month +
                ", year=" + year +
                ", avgClosePrice=" + avgClosePrice +
                '}';
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public float getAvgClosePrice() {
        return avgClosePrice;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setAvgClosePrice(float avgClosePrice) {
        this.avgClosePrice = avgClosePrice;
    }
}
