package com.solution.database.Model;

public class YearwiseMostProfitObj {

    private String sector;
    private float profit;

    public YearwiseMostProfitObj() {
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public float getProfit() {
        return profit;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }

    @Override
    public String toString() {
        return "YearwiseMostProfitObj{" +
                ", sector='" + sector + '\'' +
                ", profit=" + profit +
                '}';
    }
}
