package com.solution.database.Model;

public class MonthwiseIndustryGrowthObj {
    private int month;
    private float avgClosePrice;

    public MonthwiseIndustryGrowthObj() {

    }

    public MonthwiseIndustryGrowthObj(int month, float avgClosePrice) {
        this.month = month;
        this.avgClosePrice = avgClosePrice;
    }

    @Override
    public String toString() {
        return "MonthwiseIndustryGrowthObj{" +
                "month=" + month +
                ", avgClosePrice=" + avgClosePrice +
                '}';
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public float getAvgClosePrice() {
        return avgClosePrice;
    }

    public void setAvgClosePrice(float avgClosePrice) {
        this.avgClosePrice = avgClosePrice;
    }
}
